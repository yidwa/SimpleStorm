package tuple;

public interface Tuple extends ITuple{

    /**
     * Returns the global stream id (component + stream) of this tuple.
     * 
     * @deprecated replaced by {@link #getSourceGlobalStreamId()} due to broken naming convention
     */
    @Deprecated
    public GlobalStreamId getSourceGlobalStreamid();
    
    /**
     * Returns the global stream id (component + stream) of this tuple.
     */
    public GlobalStreamId getSourceGlobalStreamId();

    /**
     * Gets the id of the component that created this tuple.
     */
    public String getSourceComponent();
    
    /**
     * Gets the id of the task that created this tuple.
     */
    public int getSourceTask();
    
    /**
     * Gets the id of the stream that this tuple was emitted to.
     */
    public String getSourceStreamId();
    
    /**
     * Gets the message id that associated with this tuple.
     */
    public MessageId getMessageId();
}
