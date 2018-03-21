package parser;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CASTNodeListener implements CListener {

	List<Node> list;
	public CASTNodeListener(List<Node> list) {
		this.list = list;
	}

	@Override
	public void enterPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterGenericSelection(CParser.GenericSelectionContext ctx) {
	//	addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitGenericSelection(CParser.GenericSelectionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterGenericAssocList(CParser.GenericAssocListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitGenericAssocList(CParser.GenericAssocListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterGenericAssociation(CParser.GenericAssociationContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitGenericAssociation(CParser.GenericAssociationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterPostfixExpression(CParser.PostfixExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitPostfixExpression(CParser.PostfixExpressionContext ctx) {
		
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterUnaryExpression(CParser.UnaryExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitUnaryExpression(CParser.UnaryExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterUnaryOperator(CParser.UnaryOperatorContext ctx) {
		
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitUnaryOperator(CParser.UnaryOperatorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterCastExpression(CParser.CastExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitCastExpression(CParser.CastExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterAdditiveExpression(CParser.AdditiveExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitAdditiveExpression(CParser.AdditiveExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterShiftExpression(CParser.ShiftExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitShiftExpression(CParser.ShiftExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterRelationalExpression(CParser.RelationalExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitRelationalExpression(CParser.RelationalExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterEqualityExpression(CParser.EqualityExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitEqualityExpression(CParser.EqualityExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterAndExpression(CParser.AndExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitAndExpression(CParser.AndExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterExclusiveOrExpression(CParser.ExclusiveOrExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitExclusiveOrExpression(CParser.ExclusiveOrExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterInclusiveOrExpression(CParser.InclusiveOrExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitInclusiveOrExpression(CParser.InclusiveOrExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterConditionalExpression(CParser.ConditionalExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitConditionalExpression(CParser.ConditionalExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterAssignmentExpression(CParser.AssignmentExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitAssignmentExpression(CParser.AssignmentExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterAssignmentOperator(CParser.AssignmentOperatorContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitAssignmentOperator(CParser.AssignmentOperatorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterExpression(CParser.ExpressionContext ctx) {
	//	addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitExpression(CParser.ExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterConstantExpression(CParser.ConstantExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitConstantExpression(CParser.ConstantExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDeclaration(CParser.DeclarationContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDeclaration(CParser.DeclarationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDeclarationSpecifiers(CParser.DeclarationSpecifiersContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDeclarationSpecifiers(CParser.DeclarationSpecifiersContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDeclarationSpecifiers2(CParser.DeclarationSpecifiers2Context ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDeclarationSpecifiers2(CParser.DeclarationSpecifiers2Context ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDeclarationSpecifier(CParser.DeclarationSpecifierContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDeclarationSpecifier(CParser.DeclarationSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterInitDeclarator(CParser.InitDeclaratorContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitInitDeclarator(CParser.InitDeclaratorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStorageClassSpecifier(CParser.StorageClassSpecifierContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStorageClassSpecifier(CParser.StorageClassSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterTypeSpecifier(CParser.TypeSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitTypeSpecifier(CParser.TypeSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStructOrUnion(CParser.StructOrUnionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStructOrUnion(CParser.StructOrUnionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStructDeclarationList(CParser.StructDeclarationListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStructDeclarationList(CParser.StructDeclarationListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStructDeclaration(CParser.StructDeclarationContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStructDeclaration(CParser.StructDeclarationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterSpecifierQualifierList(CParser.SpecifierQualifierListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitSpecifierQualifierList(CParser.SpecifierQualifierListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStructDeclaratorList(CParser.StructDeclaratorListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStructDeclaratorList(CParser.StructDeclaratorListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStructDeclarator(CParser.StructDeclaratorContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStructDeclarator(CParser.StructDeclaratorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterEnumSpecifier(CParser.EnumSpecifierContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitEnumSpecifier(CParser.EnumSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterEnumeratorList(CParser.EnumeratorListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitEnumeratorList(CParser.EnumeratorListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterEnumerator(CParser.EnumeratorContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitEnumerator(CParser.EnumeratorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterEnumerationConstant(CParser.EnumerationConstantContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitEnumerationConstant(CParser.EnumerationConstantContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterAtomicTypeSpecifier(CParser.AtomicTypeSpecifierContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitAtomicTypeSpecifier(CParser.AtomicTypeSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterTypeQualifier(CParser.TypeQualifierContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitTypeQualifier(CParser.TypeQualifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterFunctionSpecifier(CParser.FunctionSpecifierContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitFunctionSpecifier(CParser.FunctionSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterAlignmentSpecifier(CParser.AlignmentSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitAlignmentSpecifier(CParser.AlignmentSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDeclarator(CParser.DeclaratorContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDeclarator(CParser.DeclaratorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterGccDeclaratorExtension(CParser.GccDeclaratorExtensionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitGccDeclaratorExtension(CParser.GccDeclaratorExtensionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterGccAttributeSpecifier(CParser.GccAttributeSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitGccAttributeSpecifier(CParser.GccAttributeSpecifierContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterGccAttributeList(CParser.GccAttributeListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitGccAttributeList(CParser.GccAttributeListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterGccAttribute(CParser.GccAttributeContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitGccAttribute(CParser.GccAttributeContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterNestedParenthesesBlock(CParser.NestedParenthesesBlockContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitNestedParenthesesBlock(CParser.NestedParenthesesBlockContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterPointer(CParser.PointerContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitPointer(CParser.PointerContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterTypeQualifierList(CParser.TypeQualifierListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitTypeQualifierList(CParser.TypeQualifierListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterParameterTypeList(CParser.ParameterTypeListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitParameterTypeList(CParser.ParameterTypeListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterParameterList(CParser.ParameterListContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitParameterList(CParser.ParameterListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterParameterDeclaration(CParser.ParameterDeclarationContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitParameterDeclaration(CParser.ParameterDeclarationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterIdentifierList(CParser.IdentifierListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitIdentifierList(CParser.IdentifierListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterTypeName(CParser.TypeNameContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitTypeName(CParser.TypeNameContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterAbstractDeclarator(CParser.AbstractDeclaratorContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitAbstractDeclarator(CParser.AbstractDeclaratorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDirectAbstractDeclarator(CParser.DirectAbstractDeclaratorContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDirectAbstractDeclarator(CParser.DirectAbstractDeclaratorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterTypedefName(CParser.TypedefNameContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitTypedefName(CParser.TypedefNameContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterInitializer(CParser.InitializerContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitInitializer(CParser.InitializerContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterInitializerList(CParser.InitializerListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitInitializerList(CParser.InitializerListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDesignation(CParser.DesignationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDesignation(CParser.DesignationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDesignatorList(CParser.DesignatorListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDesignatorList(CParser.DesignatorListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDesignator(CParser.DesignatorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDesignator(CParser.DesignatorContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStaticAssertDeclaration(CParser.StaticAssertDeclarationContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStaticAssertDeclaration(CParser.StaticAssertDeclarationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterStatement(CParser.StatementContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitStatement(CParser.StatementContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterLabeledStatement(CParser.LabeledStatementContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitLabeledStatement(CParser.LabeledStatementContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterCompoundStatement(CParser.CompoundStatementContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitCompoundStatement(CParser.CompoundStatementContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterBlockItemList(CParser.BlockItemListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitBlockItemList(CParser.BlockItemListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterBlockItem(CParser.BlockItemContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitBlockItem(CParser.BlockItemContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterExpressionStatement(CParser.ExpressionStatementContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitExpressionStatement(CParser.ExpressionStatementContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitSelectionStatement(CParser.SelectionStatementContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterIterationStatement(CParser.IterationStatementContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitIterationStatement(CParser.IterationStatementContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterForCondition(CParser.ForConditionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitForCondition(CParser.ForConditionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterForDeclaration(CParser.ForDeclarationContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitForDeclaration(CParser.ForDeclarationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterForExpression(CParser.ForExpressionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitForExpression(CParser.ForExpressionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterJumpStatement(CParser.JumpStatementContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitJumpStatement(CParser.JumpStatementContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterCompilationUnit(CParser.CompilationUnitContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitCompilationUnit(CParser.CompilationUnitContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterTranslationUnit(CParser.TranslationUnitContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitTranslationUnit(CParser.TranslationUnitContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterExternalDeclaration(CParser.ExternalDeclarationContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitExternalDeclaration(CParser.ExternalDeclarationContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
		addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterDeclarationList(CParser.DeclarationListContext ctx) {
		//addWhereRequired(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void exitDeclarationList(CParser.DeclarationListContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override

	public void exitEveryRule(ParserRuleContext ctx) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void visitTerminal(TerminalNode node) {
		// doNothing
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation does nothing.
	 * </p>
	 */
	@Override
	public void visitErrorNode(ErrorNode node) {
		// doNothing
	}

	protected void addWhereRequired(ParserRuleContext ctx) {
		Node n = new Node(ctx.getStart().getLine(),ctx.getStop().getLine());
		n.setClassName(ctx.getClass().toString());
		n.setHash(calculate(ctx.toStringTree()));
		list.add(n);
	}
 
	protected static int calculate(String s) {
		String[] s1 = s.split(" ");
		int sum = 0;
		for (String str : s1) {
			if (isInteger(str)) {
				sum = (sum % Integer.MAX_VALUE + Integer.parseInt(str) % Integer.MAX_VALUE) % Integer.MAX_VALUE;
	 		}
		} 
		return sum;
	}

	protected static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	protected static boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}

}
