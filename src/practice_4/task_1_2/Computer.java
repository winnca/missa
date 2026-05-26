package practice_4.task_1_2;

public class Computer {
    private final String cpu;
    private final int ram;
    private final int storage;
    private final String gpu;
    private final boolean ssd;
    private Computer(Builder builder){
        this.cpu= builder.cpu;
        this.ram= builder.ram;
        this.storage= builder.storage;
        this.gpu= builder.gpu;
        this.ssd= builder.ssd;
    }
    static class Builder{
        private final String cpu;
        private final int ram;
        private int storage = 256;
        private String gpu = null;
        private boolean ssd = true;
        Builder (String cpu, int ram){
            this.cpu=cpu;
            this.ram=ram;
        }
        Builder storage(int storage){
            this.storage=storage;
            return this;
        }
        Builder gpu(String gpu){
            this.gpu=gpu;
            return this;
        }
        Builder ssd(boolean ssd){
            this.ssd=ssd;
            return this;
        }
        Computer build(){return new Computer(this);}
    }
    @Override
    public String toString() {
        String storageType = ssd ? "SSD":"HDD";
        String gpuType = (gpu == null || gpu.isEmpty()) ? "встроенная" : "'" + gpu + "'";
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram=" + ram + "GB" +
                ", storage=" + storage + "GB" + storageType +
                ", gpu='" + gpuType + '\'' + '}';
    }
    public static void main(String[] args){
        Computer computerPlay = new Builder("Core i9", 32).storage(2000).gpu("RTX 4080").ssd(true).build();
        System.out.println(computerPlay);
        Computer computerOffice = new Builder("Core i5", 16).build();
        System.out.println(computerOffice);
    }
}