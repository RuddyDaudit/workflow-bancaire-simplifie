class Workflow {
    double montant;
    String status;

    public Workflow(double montant){
        this.montant = montant;
        this.status = "CREATED";
    }

    @Override
    public String toString(){
        return "Transaction { Montant = " + montant + ", Statut = " + status + " }";
    }
}

class FrontOffice {
    public Workflow createTransaction(double montant){
        Workflow w = new Workflow(montant);
        System.out.println("Front Office → transaction créée : " + montant);
        return w;
    }
}

class MiddleOffice {
    public Workflow validationTransaction(Workflow w){
        if (w.montant > 1000){
            w.status = "REJECTED";
            System.out.println("Middle Office → rejet : " + w.montant);
        } else {
            w.status = "VALIDATED";
            System.out.println("Middle Office → validation : " + w.montant);
        }
        return w;
    }
}

class BackOffice {
    public void confirmationTransaction(Workflow w){
        if ("VALIDATED".equals(w.status)){
            w.status = "SETTLED";
            System.out.println("Back Office → transaction confirmée et réglée");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        FrontOffice fo = new FrontOffice();
        MiddleOffice mo = new MiddleOffice();
        BackOffice bo = new BackOffice();

        Workflow tx = fo.createTransaction(500);
        tx = mo.validationTransaction(tx);
        bo.confirmationTransaction(tx);

        System.out.println("Status final : " + tx.status);
    }
}
