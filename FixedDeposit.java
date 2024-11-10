class FixedDeposit{
	private String fdID;
	private static long nextID = 1;
	private double principalAmount;
	private LocalDate maturityDate;
	private LocalDate startDate;
	private double interest;
	private double penalty;
	private double maturityAmount;
	private String status;
	FixedDeposit(double amount, int months, double penalty, double interest){
		fdID = "FD" + String.format("%09d",nextID++);
		principalAmount = amount;
		this.maturityDate = LocalDate.now().plusMonths(months);
		this.startDate = LocalDate.now();
		this.penalty = penalty;
		this.interest = interest;
		int monthsBetween = (int) ChronoUnit.MONTHS.between(startDate, maturityDate);
		maturityAmount = principalAmount + (principalAmount * interest * monthsBetween / 100);
		this.status = "open";
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
	void withdrawFD(Account){
		if(status.equal("open")){
			LocalDate currentDate = LocalDate.now();
			if(currentDate.isAfter(maturityDate) || currentDate.isEqual(maturityDate)){
				Account.deposit(maturityAmount);
			}
			else{
				int monthsBetween = (int) ChronoUnit.MONTHS.between(startDate, currentDate);
				maturityAmount = (principalAmount * interest * monthsBetween/ 100);
				maturityAmount -= (maturity * penalty / 100);
				Account.deposit(maturityAmount);
				status = "close";
			}
		}
		else{
			System.out.println("Fixed Deposit " + this.fdID + " has been closed");
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
	void increaseTenure(int months){
		if(status.equal("open")){
			this.maturityDate = maturityDate.plusMonths(months);
		}
		else{
			System.out.println("Fixed Deposit " + this.fdID + " has been closed");
		}
	}
}
