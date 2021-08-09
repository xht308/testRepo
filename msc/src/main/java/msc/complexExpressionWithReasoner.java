package msc;

import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.*;

public class complexExpressionWithReasoner {

	public static void main(String[] args) throws OWLException {
		//Create a new ontology
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		IRI IOR = IRI.create("http://owl.api.tutorial");
		OWLOntology ont = man.createOntology(IOR);
		OWLDataFactory df = ont.getOWLOntologyManager().getOWLDataFactory();
		//Prepare classes and object property
		OWLClass student = df.getOWLClass(IRI.create(IOR+"#Student"));
		OWLClass person = df.getOWLClass(IRI.create(IOR+"#Person"));
		OWLClass university = df.getOWLClass(IRI.create(IOR+"#University"));
		OWLClass course = df.getOWLClass(IRI.create(IOR+"#Course"));
		OWLObjectProperty isEnrolledIn = df.getOWLObjectProperty(IOR+"#isEnrolledIn");
		OWLObjectProperty attends = df.getOWLObjectProperty(IOR+"#attends");
		//Add equivalent classes axiom
		OWLEquivalentClassesAxiom ax = df.getOWLEquivalentClassesAxiom(student, person,df.getOWLObjectIntersectionOf(df.getOWLObjectSomeValuesFrom(isEnrolledIn, university),df.getOWLObjectSomeValuesFrom(attends, course)));
		ont.add(ax);
		//Add named individuals
		OWLNamedIndividual X = df.getOWLNamedIndividual(IRI.create(IOR+"#X"));
		ont.add(df.getOWLClassAssertionAxiom(person, X));
		OWLNamedIndividual WKU = df.getOWLNamedIndividual(IRI.create(IOR+"#WKU"));
		ont.add(df.getOWLClassAssertionAxiom(university, WKU));
		//Property assertion
		ont.add(df.getOWLObjectPropertyAssertionAxiom(isEnrolledIn, X, WKU));
		ont.add(df.getOWLObjectPropertyAssertionAxiom(attends, X, df.getOWLAnonymousIndividual())); //Not sure
		//print logical axioms
		ont.logicalAxioms().forEach(System.out::println);
		//Instantiate the reasoner
		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(ont);
		//Q: Is the person X a student or not?
		//using the OWLOntology interface alone
		boolean flag = false;
		for (OWLAxiom owlAxiom:ont.getAxioms(student)) {
			if (!owlAxiom.isOfType(AxiomType.EQUIVALENT_CLASSES)) continue;
			for (OWLClass owlClass:owlAxiom.getClassesInSignature()) {
				if (owlClass.equals(X)) continue;
				if (ont.containsAxiom(df.getOWLClassAssertionAxiom(owlClass, X))) {
					flag = true;
					break;
				}
			}
			if (flag) break;
		}
		if (flag) System.out.println("Person X is a student.");
		else System.out.println("Person X is not a student.");
		//Ask the reasoner the same question
		System.out.println();
		if (r.getInstances(student).containsEntity(X)) System.out.println("Person X is a student.");
		else System.out.println("Person X is not a student.");
				
	}

}
