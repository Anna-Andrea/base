package lind001.jds.exception;

/**
 * @author lind001
 * @date 2018/09/25
 */
public class JDSException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String errorMessage;

    // contructor  
    public JDSException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @SuppressWarnings("unused")
    private String getErrorMessage() {
        return this.errorMessage;
    }
}
