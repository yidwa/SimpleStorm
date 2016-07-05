package tuple;

public class AddressedTuple {
    /**
     * Destination used when broadcasting a tuple.
     */
    public static final int BROADCAST_DEST = -2;
    public final Tuple tuple;
    public final int dest;

    public AddressedTuple(int dest, Tuple tuple) {
        this.dest = dest;
        this.tuple = tuple;
    }

    public Tuple getTuple() {
        return tuple;
    }

    public int getDest() {
        return dest;
    }

    @Override
    public String toString() {
        return "[dest: "+dest+" tuple: "+tuple+"]";
    }
}