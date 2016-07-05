package tuple;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

public class MessageId {
    private Map<Long, Long> _anchorsToIds;
    
    public static long generateId(Random rand) {
        return rand.nextLong();
    }

    public static MessageId makeUnanchored() {
        return makeId(new HashMap<Long, Long>());
    }
        
    public static MessageId makeId(Map<Long, Long> anchorsToIds) {
        return new MessageId(anchorsToIds);
    }
        
    public static MessageId makeRootId(long id, long val) {
        Map<Long, Long> anchorsToIds = new HashMap<>();
        anchorsToIds.put(id, val);
        return new MessageId(anchorsToIds);
    }
    
    protected MessageId(Map<Long, Long> anchorsToIds) {
        _anchorsToIds = anchorsToIds;
    }

    public Map<Long, Long> getAnchorsToIds() {
        return _anchorsToIds;
    }

    public Set<Long> getAnchors() {
        return _anchorsToIds.keySet();
    }    
    
    @Override
    public int hashCode() {
        return _anchorsToIds.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof MessageId && _anchorsToIds.equals(((MessageId) other)._anchorsToIds);
    }

    @Override
    public String toString() {
        return _anchorsToIds.toString();
    }

    public void serialize(Output out) throws IOException {
        out.writeInt(_anchorsToIds.size(), true);
        for(Entry<Long, Long> anchorToId: _anchorsToIds.entrySet()) {
            out.writeLong(anchorToId.getKey());
            out.writeLong(anchorToId.getValue());
        }
    }

    public static MessageId deserialize(Input in) throws IOException {
        int numAnchors = in.readInt(true);
        Map<Long, Long> anchorsToIds = new HashMap<>();
        for(int i=0; i<numAnchors; i++) {
            anchorsToIds.put(in.readLong(), in.readLong());
        }
        return new MessageId(anchorsToIds);
    }
}