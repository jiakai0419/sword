package sword.lib.dict;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

/**
 * @Author KaiJia
 * @DATE 2016-11-30
 * @DESC dynamic dict
 */
@Slf4j
public class DyncDict<K, V> {
    private String path;
    private Function<String, Pair<K, V>> parser;
    private Map<K, V> dict;
    private Timer timer;
    private String encoding;
    private boolean initialized;

    public DyncDict(String path, Function<String, Pair<K, V>> parser, long period, String encoding) {
        this.path = path;
        this.parser = parser;
        this.dict = new HashMap<>();
        this.timer = new Timer();
        this.encoding = encoding;
        this.initialized = false;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reload();
            }
        }, 0, period);
    }

    protected void reload() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            log.info("reload begin path[{}]", path);
            fis = new FileInputStream(path);
            isr = new InputStreamReader(fis, encoding);
            br = new BufferedReader(isr);
            Map<K, V> newDict = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                Pair<K, V> pair;
                try {
                    pair = parser.apply(line);
                    log.debug("parse success line[{}] pair[{}]", line, new Gson().toJson(pair));
                } catch (Throwable t) {
                    log.error("parse failed line[{}]", line);
                    throw t;
                }
                newDict.put(pair.getLeft(), pair.getRight());
            }
            dict = newDict;
            initialized = true;
            log.debug("final dict {}", new Gson().toJson(dict));
            log.info("reload success path[{}] size[{}]", path, dict.size());
        } catch (Throwable t) {
            log.error("reload failed path[{}] {}", path, ExceptionUtils.getStackTrace(t));
        } finally {
            close(br);
            close(isr);
            close(fis);
        }
    }

    private void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Throwable t) {
            log.error("free resource failed", t);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        timer.cancel();
    }

    public V get(Object key) {
        return dict.get(key);
    }

    public V put(K key, V value) {
        return dict.put(key, value);
    }

    public boolean containsKey(Object key) {
        return dict.containsKey(key);
    }

    public boolean isInitialized() {
        return initialized;
    }

    public Map<K, V> snapshot() {
        return dict;
    }
}
