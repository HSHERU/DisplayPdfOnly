package displaypdfonly.abdulrahmanaldehan.com.displaypdfonly;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * For identifying current view mode read/create/listing/options
     * @author androidsrc.net
     *
     */
    //index to track currentPage in rendered Pdf
    private static int currentPage = 0;
    //View on which Pdf content will be rendered
    ImageView pdfView;

    //For opening current page, render it, and close the pag
    PdfRenderer.Page mCurrentPage;

    ParcelFileDescriptor mFileDescriptor;
    // PdfRenderer enables rendering a PDF document
    PdfRenderer mPdfRenderer;
    //Currently rendered Pdf file
    String openedPdfFileName;
    Button generatePdf;
    Button next;
    Button goTo_Tadabbur_page_button;
    Button previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfView = (ImageView) findViewById(R.id.pdfView);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
        previous = (Button) findViewById(R.id.previous);
        previous.setOnClickListener(this);
        goTo_Tadabbur_page_button = (Button) findViewById(R.id.goTo_tadabbur_page);
        goTo_Tadabbur_page_button.setOnClickListener(this);

        openedPdfFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/standard1-quran.pdf";
        if(openRenderer(openedPdfFileName))
            showPage(1);
    }

    /**
     * Callback for handling view click events
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.next:
                currentPage++;
                showPage(currentPage);
                break;
            case R.id.previous:
                currentPage--;
                showPage(currentPage);
                break;
            case R.id.goTo_tadabbur_page:
                Intent intent = new Intent(MainActivity.this , Tadabbur_page.class);
                startActivity(intent);
        }

    }

    private void showPage(int index) {
        if (mPdfRenderer == null)
            return;
        if(mPdfRenderer.getPageCount() <= index || index < 0) {
            return;
        }
        // For closing the current page before opening another one.
        try {
            if (mCurrentPage != null) {
                mCurrentPage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Open page with specified index
        mCurrentPage = mPdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(), Bitmap.Config.ARGB_8888);

        //Pdf page is rendered on Bitmap
        mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        //Set rendered bitmap to ImageView
        pdfView.setImageBitmap(bitmap);
        updateActionBarText();
    }

    /**
     * API to update ActionBar text
     */
    private void updateActionBarText()
    {
            int index = mCurrentPage.getIndex();
            int pageCount = mPdfRenderer.getPageCount();
            previous.setEnabled(0 != index);
            next.setEnabled(index + 1 < pageCount);
            //getActionBar().setTitle(openedPdfFileName + "(" + (index + 1) + "/" + pageCount + ")");
    }

    private boolean openRenderer(String filePath)
    {
        File file = new File(filePath);
        try {
            mFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "error catched", Toast.LENGTH_SHORT).show();
        }
        return false;
    }



}
