package server;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import com.gdevelop.gwt.syncrpc.SyncProxy;
import com.google.musicstore.client.MusicStoreService;
import com.google.musicstore.client.dto.AccountDTO;
import com.google.musicstore.server.MusicStoreServiceImpl;

public class TestMusicStoreService {
    @BeforeClass
    public static void setUp() throws Exception {
	Server _server = new Server(8080);
	Context root = new Context(_server, "/", Context.SESSIONS);
	root.addServlet(new ServletHolder(new MusicStoreServiceImpl()), "/musicService");
	_server.start();
    }

    @Test
    public void testGetAccounts() {
	MusicStoreService rpcService = (MusicStoreService) SyncProxy.newProxyInstance(MusicStoreService.class, "http://127.0.0.1:8080/servlet", "getAccounts");

        List<AccountDTO> accounts = rpcService.getAccounts();

        assertTrue(accounts.size() == 245);
    }
}
