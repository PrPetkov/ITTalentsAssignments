package cases;

import java.util.HashSet;

import interfaces.IProsecutor;
import outputBuilder.OutpuBuilder;
import people.*;

public abstract class Case {

	protected final String caseArchive;
	
	private Judge judge;
	private HashSet<Jury> jury;
	protected Defendant defendant;
	protected IProsecutor prosecutor;
	protected HashSet<Witness> witneses;
	private boolean isGuilty = false;
	
	public Case(Judge judge, HashSet<Jury> jury, Defendant defendant, IProsecutor prosecutor,
			HashSet<Witness> witneses, String caseArchive) {

		this.setJudge(judge);
		this.setJury(jury);
		this.setDefendant(defendant);
		this.setProsecutor(prosecutor);
		this.setWitneses(witneses);
		if (caseArchive == null) {
			throw new IllegalArgumentException("Archive name can not be null");
		}
		this.caseArchive = caseArchive;
	}

 	protected Judge getJudge() {
		return judge;
	}

	protected void setJudge(Judge judge) {
		if (judge == null) {
			throw new IllegalArgumentException("The judge can not be null");
		}
		
		this.judge = judge;
	}

	protected HashSet<Jury> getJury() {
		return jury;
	}

	protected void setJury(HashSet<Jury> jury) {
		if (jury == null) {
			throw new IllegalArgumentException("The jury can not be null");
		}
		
		this.jury = jury;
	}

	protected Defendant getDefendant() {
		return defendant;
	}

	protected void setDefendant(Defendant defendant) {
		if (defendant == null) {
			throw new IllegalArgumentException("The defendant can not be null");
		}
		
		this.defendant = defendant;
	}

	protected IProsecutor getProsecutor() {
		return prosecutor;
	}

	protected void setProsecutor(IProsecutor prosecutor) {
		if (prosecutor == null) {
			throw new IllegalArgumentException("The prosecutor can not be null");
		}
		
		this.prosecutor = prosecutor;
	}

	protected HashSet<Witness> getWitneses() {
		return witneses;
	}

	protected void setWitneses(HashSet<Witness> witneses) {

		if (witneses == null) {
			throw new IllegalArgumentException("The witneses can not be null");
		}
		
		this.witneses = witneses;
	}

	public void execute(){
		
		this.writeCaseParticipants();
		
		this.addCases();
		
		this.askQuestions();
		
		this.defineGuilty();
		
		if (this.isGuilty) {
		
			this.defineSentence();
			
		} else {
			this.judge.takeNotes("Innocent", this.caseArchive);
		}
		
		this.writeCaseParticipants();
		
	}
	
	private void defineSentence() {
		
		this.judge.takeNotes(this.judge.defineSentance() + " years in prison.", this.caseArchive);
		
	}

	private void defineGuilty() {
		int votesForQuilty = 0;
		
		for (Jury jury : jury) {
			boolean descision = jury.defineGuilty();
			if (descision) {
				votesForQuilty++;
				jury.takeNotes(jury.getName() + " - " + "quilty", this.caseArchive);
			} else {
				votesForQuilty--;
				jury.takeNotes(jury.getName() + " - " + "not quilty", this.caseArchive);
			}
		}
		
		if (votesForQuilty > 0) {
			this.isGuilty = true;
		}
	}

	protected abstract void askQuestions();
	
	private void writeCaseParticipants(){
		//append participants
		StringBuilder builder = new StringBuilder();
		builder.append("Judge: ");
		builder.append(this.judge.getName());
		builder.append(System.lineSeparator());
		builder.append("Jury: ");
		builder.append(System.lineSeparator());
		for (Jury jury : jury) {
			builder.append(jury.getName());
			builder.append(System.lineSeparator());
		}
		
		builder.append("Defendant: ");
		builder.append(this.defendant.getName());
		builder.append(System.lineSeparator());
		builder.append(this.prosecutor.getClass().getSimpleName());
		builder.append(" : ");
		builder.append(this.prosecutor.getName());
		builder.append(System.lineSeparator());
		builder.append("Witneses: ");
		builder.append(System.lineSeparator());
		for (Witness witness : witneses) {
			builder.append(witness.getName());
			builder.append(System.lineSeparator());
		}
				
		OutpuBuilder.appendLine(builder.toString(), this.caseArchive);
	}
	
	protected void addCases(){
		
		this.judge.addCase();
		for (Jury jury : jury) {
			jury.addCase();
		}
		
		if (this.prosecutor instanceof Attorney) {
			((Attorney) this.prosecutor).addCase();
		}
		
		for( Lawyer lawyer : this.defendant.getLawyers()) {
			lawyer.addCase();
		}		
	}
}
