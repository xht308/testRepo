package msc;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class createNewOntology {

	public static void main(String[] args) {
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		OWLOntology o;
		try {
			o = man.createOntology(); 
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
