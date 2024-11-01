class FixedDeposit{
	private int fdID = 0;
	private static nextID = 1;
	private double principalAmount;
	private int tenure;
	private double interest;
	private double penalty;
	private double maturityAmount;
	private int remainingTenure;
	private String status;
	FixedDeposit(double amount, int tenure, double penalty, double interest){
		fdID = nextID++;
		principalAmount = amount;
		this.tenure = tenure;
		this.penalty = penalty;
		this.interest = interest;
		status = "open";
		remainingTenure = tenure;
	}
	void calculateMaturityAmount(){
		if (status.equals("open")){
			maturityAmount = principalAmount + (principalAmount * interest / 100);
		}
		else{
			System.out.println("Fixed Deposit " + this.fdID + " has been closed");
		}
	}
	double getMaturityAmount(){
		if (status.equals("open")){
			return maturityAmount;
		}
		else{
			System.out.println("Fixed Deposit " + this.fdID + " has been closed");
			return 0;
		}
	}
	double withdrawPrematurely(){
		if(status.equal("open")){
			maturityAmount -= (maturityAmount * penalty / 100);
			status = "close";
			remainingTenure = 0;
			return maturityAmount;
		}
		else{
			System.out.println("Fixed Deposit " + this.fdID + " has been closed");
			return 0;
		}
	}
	void updateStatus(String status){
		if (!this.status.equals(status)){
			this.status = status;
		}
	}
	void uodateInterest(double interest){
		if(status.equal("open")){
			this.interest = interest;
		}
		else{
			System.out.println("Fixed Deposit " + this.fdID + " has been closed");
		}
	}
	void updatePenalty(double penalty){
		if(status.equal("open")){
			this.penalty = penalty;
		}
		else{
			System.out.println("Fixed Deposit " + this.fdID + " has been closed");
		}
	}
	void increaseTenure(int tenure){
		if(status.equal("open")){
			this.tenure = tenure;
		}
		else{
			System.out.println("Fixed Deposit " + this.fdID + " has been closed");
		}
	}
}
