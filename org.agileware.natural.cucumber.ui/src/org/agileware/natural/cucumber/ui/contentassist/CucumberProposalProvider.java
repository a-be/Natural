/*
* generated by Xtext
*/
package org.agileware.natural.cucumber.ui.contentassist;

import java.util.Collection;

import org.agileware.natural.common.AbstractAnnotationDescriptor;
import org.agileware.natural.common.JavaAnnotationMatcher;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.impl.RuleCallImpl;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import com.google.inject.Inject;

/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
public class CucumberProposalProvider extends AbstractCucumberProposalProvider {

	@Inject
	private JavaAnnotationMatcher matcher;
	
	@Inject
	private AbstractAnnotationDescriptor descriptor;
	
	public void complete_Step(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if (((RuleCallImpl)context.getLastCompleteNode().getGrammarElement()).getRule().getName().equals("EOL")) {
			for (String entry : descriptor.getNames()) {
				acceptor.accept(createCompletionProposal(entry, context));
			}
		}
	}
	
	public void complete_StepDescription(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		Collection<String> proposals = matcher.findProposals();
		for (String entry : proposals) {
			if (entry.charAt(0) == '^') {
				entry = entry.substring(1);
			}
			if (entry.charAt(entry.length() - 1) == '$') {
				entry = entry.substring(0, entry.length() - 2);
			}
			acceptor.accept(createCompletionProposal(entry, context));
		}
	}
}
