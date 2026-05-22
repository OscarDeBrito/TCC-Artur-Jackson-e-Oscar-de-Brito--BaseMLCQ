public class Snippet__8102677 {

    public final void rule__GeneratedMetamodel__EPackageAssignment_2() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalXtext.g:8603:1: ( ( ( RULE_STRING ) ) )
                // InternalXtext.g:8604:2: ( ( RULE_STRING ) )
                {
                // InternalXtext.g:8604:2: ( ( RULE_STRING ) )
                // InternalXtext.g:8605:3: ( RULE_STRING )
                {
                 before(grammarAccess.getGeneratedMetamodelAccess().getEPackageEPackageCrossReference_2_0()); 
                // InternalXtext.g:8606:3: ( RULE_STRING )
                // InternalXtext.g:8607:4: RULE_STRING
                {
                 before(grammarAccess.getGeneratedMetamodelAccess().getEPackageEPackageSTRINGTerminalRuleCall_2_0_1()); 
                match(input,RULE_STRING,FollowSets000.FOLLOW_2); 
                 after(grammarAccess.getGeneratedMetamodelAccess().getEPackageEPackageSTRINGTerminalRuleCall_2_0_1()); 

                }

                 after(grammarAccess.getGeneratedMetamodelAccess().getEPackageEPackageCrossReference_2_0()); 

                }


                }

            }
            catch (RecognitionException re) {
                reportError(re);
                recover(input,re);
            }
            finally {

                	restoreStackSize(stackSize);

            }
            return ;
        }

}
