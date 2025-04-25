
package protect.card_locker;

import static org.junit.Assert.assertFalse;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(RobolectricTestRunner.class)
public class LoyaltyCardShapeTest {
    private Activity activity;
    private SQLiteDatabase mDatabase;


    // Set-up includes running the main activity and creating an empty database
    // allowing us to freely call app methods and classes as if they were running
    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
        mDatabase = TestHelpers.getEmptyDb(activity).getWritableDatabase();
    }

    // simple test to see if the added boolean field "isRounded" was added
    // it is false by default so a newly created card should have it as that
    @Test
    public void LoyaltyCardIsRoundedTest() {

        Date date = new Date();

        // create and insert a new card into the empty DB
        DBHelper.insertLoyaltyCard(mDatabase, "store", "This note contains evil symbols like & and = that will break the parser if not escaped right $#!%()*+;:á", date, date, new BigDecimal("100"), null, BarcodeFormat.UPC_E.toString(), BarcodeFormat.UPC_A.toString(), CatimaBarcode.fromBarcode(BarcodeFormat.QR_CODE), Color.BLACK, 1, null,0);

        // Get that card
        LoyaltyCard card = DBHelper.getLoyaltyCard(activity.getApplicationContext(), mDatabase, 1);

        // check if the newly added boolean field "isRounded" was initialized correctly
        assertFalse(card.isRounded);

    }
}
