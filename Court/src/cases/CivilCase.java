package cases;

import java.util.HashSet;

import people.*;

public class CivilCase extends Case {

	public CivilCase(Judge judge, HashSet<Jury> jury, Defendant defendant, Prosecutor prosecutor,
			HashSet<Witness> witneses, String caseArchive) {
		super(judge, jury, defendant, prosecutor, witneses, caseArchive);
		
	}
	
	@Override
	public void askQuestions(){
		for (Lawyer lawyer : ((Prosecutor)super.prosecutor).getLawyers()) {
			StringBuilder builder = new StringBuilder();
			
			for (int i = 0; i < 3; i++) {
				builder.append(lawyer.askQuestion(super.defendant));
				builder.append(System.lineSeparator());
			}

			for (Witness witness : super.witneses) {
				builder.append(lawyer.askQuestion(witness));
				builder.append(System.lineSeparator());
				builder.append(lawyer.askQuestion(witness));
				builder.append(System.lineSeparator());
			}	
			
			lawyer.takeNotes(builder.toString(), super.caseArchive);
		}
	}

	@Override
	protected void addCases() {
		super.addCases();
		
		for (Lawyer lawyer : ((Prosecutor)(super.prosecutor)).getLawyers()) {
			lawyer.addCase();
		}
		
	}
}
