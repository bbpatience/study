package producer;

/**
 * @author: bbpatience
 * @date: 2018/12/12
 * @description: PCData
 **/
public final class PCData {
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d) {
        this.intData = Integer.parseInt(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data " +  intData;
    }
}
