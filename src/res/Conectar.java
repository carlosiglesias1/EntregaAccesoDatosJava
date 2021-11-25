package res;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Conectar implements ConnectionPool {
    private static final int MAX_POOL_SIZE = 10;
    private static final int MAX_TIMEOUT = 1000;
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 10;
    private Conectar bcp = null;

    private Conectar(String url, String user, String password, List<Connection> pool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = pool;
    }

    
    /** 
     * @param url
     * @param user
     * @param password
     * @return Conectar
     * @throws SQLException
     */
    public static Conectar create(String url, String user, String password) throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new Conectar(url, user, password, pool);
    }

    
    /** 
     * @return Connection
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection(url, user, password));
            } else {
                throw new SQLException("Maximum pool size reached, noavailable connections!");
            }
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        if (!connection.isValid(MAX_TIMEOUT)) {
            connection = createConnection(url, user, password);
        }
        usedConnections.add(connection);
        return connection;
    }

    
    /** 
     * @param connection
     * @return boolean
     */
    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    
    /** 
     * @param url
     * @param user
     * @param password
     * @return Connection
     * @throws SQLException
     */
    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    
    /** 
     * @return int
     */
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    
    /** 
     * @return String
     */
    @Override
    public String getUrl() {
        return url;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getUser() {
        return user;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getPassword() {
        return password;
    }

    
    /** 
     * @return Conectar
     */
    public Conectar getBcp() {
        return bcp;
    }

    
    /** 
     * @throws SQLException
     */
    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
    }
}