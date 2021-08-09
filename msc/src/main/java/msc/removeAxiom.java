package msc;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class removeAxiom {

	public static void main(String[] args) throws OWLException {
		//Create an OWL Ontology
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntology o = man.createOntology(IOR);
		//Add buggy SubClassOf Axiom
		OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();
		OWLClass mann = df.getOWLClass(IOR+"#Man");
		OWLClass woman = df.getOWLClass(IOR+"#Woman");
		OWLSubClassOfAxiom m_sub_w = df.getOWLSubClassOfAxiom(mann, woman);
		o.add(m_sub_w);
		//Print Ontology
		System.out.println(o);
		//Remove Axiom
		o.remove(m_sub_w);
		//Print result
		System.out.println(o);

	}

}
