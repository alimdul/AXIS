package models;

public class FloweringTime {
    private String firstMonthFlowering;
	private String lastMonthFlowering;

	public FloweringTime(){}

	public FloweringTime(String firstMonthFlowering, String lastMonthFlowering){
	    this.firstMonthFlowering = firstMonthFlowering;
	    this.lastMonthFlowering = lastMonthFlowering;
    }

    public String getFirstMonthFlowering() {
        return firstMonthFlowering;
    }

    public void setFirstMonthFlowering(String firstMonthFlowering) {
        this.firstMonthFlowering = firstMonthFlowering;
    }

    public String getLastMonthFlowering() {
        return lastMonthFlowering;
    }

    public void setLastMonthFlowering(String lastMonthFlowering) {
        this.lastMonthFlowering = lastMonthFlowering;
    }
}
