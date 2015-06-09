/*
 * generated by Xtext
 */
package de.htwg.mdsd.xtext.portals.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IFileSystemAccess
import de.htwg.mdsd.xtext.portals.dsl.Playground

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class DslGenerator implements IGenerator {
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		stadt = resource.contents.filter(typeof(Playground)).head

		generateModel(fsa)
		fsa.generateFile(stadt.name.toFirstLower + "\\" + stadt.name.toFirstUpper + ".java", stadt.content(resource))

	//		for (bus : resource.allContents.toIterable.filter(typeof(Bus))) {
	//			fsa.generateFile(stadt.name + "\\" + "bus" + "\\" + bus.name + ".java", bus.content)
	//		}
//		fsa.generateFile('greetings.txt', 'People to greet: ' + 
//			resource.allContents
//				.filter(typeof(Greeting))
//				.map[name]
//				.join(', '))
	}
}