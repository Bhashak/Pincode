package Test;

import Base_Package.BaseClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Connections extends BaseClass {


    @Test(priority = 1)





    public void SnowFlake_Connections() throws InterruptedException, IOException {
//      connectionsPage.Add_Snowflake_Connection();
//      connectionsPage.ValidateSnowflakeConnection();
//      dqRules
//      Page.Snowflake_Table_Validation();
//      //Single DB Validation
//      dqRul
//      esPage.AddColumnsandChecks();
//      dqRulesPage.Preview_and_CreateRule();
//      dqRulesPage.Edit_AddCheck_Update();
//      dqRulesPage.Execution_Results();
        //Single Comparision
        comparisionPage.Comparision();
        comparisionPage.firstconnection();
        comparisionPage.Secondconnection();
        comparisionPage.Preview_and_CreateDQRule();
        comparisionPage.execute();
    }

    @Test(priority = 2)
    public void SQL_Connection() throws InterruptedException, IOException {
        connectionsPage.Add_SQL_Connection();
//        connectionsPage.Edit_Preview_Validation();
//        connectionsPage.ValidateConnection();
//        dqRulesPage.Table_Validation();
//        dqRulesPage.AddColumnsandChecks();
//        dqRulesPage.Preview_and_CreateRule();
//        dqRulesPage.Edit_AddCheck_Update();
//        dqRulesPage.Execution_Results();
//          comparisionPage.Comparision();
//    }

    }
}