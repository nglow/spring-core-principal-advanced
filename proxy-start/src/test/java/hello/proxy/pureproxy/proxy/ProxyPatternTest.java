package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        var realSubject = new RealSubject();
        var client = new ProxyPatternClient(realSubject);

        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        var realSubject = new RealSubject();
        var cacheProxy = new CacheProxy(realSubject);
        var client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();
    }
}
