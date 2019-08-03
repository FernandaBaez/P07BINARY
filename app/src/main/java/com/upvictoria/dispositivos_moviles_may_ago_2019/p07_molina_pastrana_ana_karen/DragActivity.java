package com.upvictoria.dispositivos_moviles_may_ago_2019.p07_molina_pastrana_ana_karen;

import android.app.Activity;
//import android.content.ClipData;
//import android.content.ClipDescription;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * This activity presents a screen with a grid on which images can be added and moved around.
 * It also defines areas on the screen where the dragged views can be dropped. Visual feedback is
 * provided to the user as the objects are dragged over the views where something can be dropped.
 *
 * <p> 
 * This example starts dragging when a DragSource view is touched. 
 * If you want to start with a long press (long click), set the variable mLongClickStartsDrag to true.
 * 
 */

public class DragActivity extends Activity 
    implements View.OnLongClickListener, View.OnClickListener,
               DragDropPresenter,
               View.OnTouchListener
{


/**
 */
// Constants

private static final int HIDE_TRASHCAN_MENU_ID = Menu.FIRST;
private static final int SHOW_TRASHCAN_MENU_ID = Menu.FIRST + 1;
private static final int ADD_OBJECT_MENU_ID = Menu.FIRST + 2;
private static final int CHANGE_TOUCH_MODE_MENU_ID = Menu.FIRST + 3;

public static final String LOG_NAME = "DragActivity";

/**
 */
// Variables

private DragController mDragController;   // Object that handles a drag-drop sequence. 
                                          // It interacts with DragSource and DropTarget objects.
//private GridView mGridView;               // the GridView
private DeleteZone mDeleteZone;           // A drop target that is used to remove objects from the screen.
private int mImageCount = 0;              // The number of images that have been added to screen.
private ImageCell mLastNewCell = null;    // The last ImageCell added to the screen when Add Image is clicked.
private boolean mLongClickStartsDrag = false;   // If true, it takes a long click to start the drag operation.
                                                // Otherwise, any touch event starts a drag.

private Vibrator mVibrator;
private static final int VIBRATE_DURATION = 35;

public static final boolean Debugging = false;   // Use this to see extra toast messages while debugging.


public Random rand = new Random(); // Variable random
public  int num = 0; // Número en decimal
public  long binary = 0; // Número en binario
public  int casillas = 0; // Cantidad de casillas a mostrar

FrameLayout imgHolder1,imgHolder2,imgHolder3,imgHolder4,imgHolder5,imgHolder6,imgHolder7,imgHolder8,imgHolder9,imgHolder10;

TextView TV_Numero; // TextView de número a calcular
Button BT_Salir; // Botón de salir al menú
Button BT_Siguiente; // Botón para ver siguiente número
AlertDialog.Builder ADX; // Alert Builder
AlertDialog AD; // ALert Dialog

ArrayList<FrameLayout> frames =new ArrayList<>();
/**
 * Metodos
 */

/**
 * Add a new image so the user can move it around. It shows up in the image_source_frame
 * part of the screen.
 * 
 * @param resourceId int - the resource id of the image to be added
 */    

public void addNewImageToScreen (int resourceId, FrameLayout imgHolder)
{
//    if (mLastNewCell != null) mLastNewCell.setVisibility (View.GONE);

    if (imgHolder != null) {
       FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams (LayoutParams.MATCH_PARENT, 
                                                                   LayoutParams.MATCH_PARENT, 
                                                                   Gravity.CENTER);
       ImageCell newView = new ImageCell (this);
       newView.setImageResource (resourceId);
       imgHolder.addView (newView, lp);
       newView.mEmpty = false;
       newView.mCellNumber = -1;
       mLastNewCell = newView;
       mImageCount++;

       // Have this activity listen to touch and click events for the view.
       newView.setOnClickListener(this);
       newView.setOnLongClickListener(this);
       newView.setOnTouchListener (this);

    }
}

/**
 * Add one of the images to the screen so the user has a new image to move around. 
 * See addImageToScreen.
 */    

public void addNewImageToScreen ()
{
    for(int i=0;i<10;i++){
        frames.get(i).removeAllViews();
    }

    int resourceIdCero = R.drawable.cero;
    int resourceIdUno = R.drawable.uno;

    String str_bin = Long.toString(binary);

    for(int i=0;i<casillas;i++) {
        if (str_bin.charAt(i) == '0') {
            addNewImageToScreen(resourceIdCero, frames.get(i));
        } else {
            addNewImageToScreen(resourceIdUno, frames.get(i));
        }
    }
}

/**
 * Handle a click on a view.
 *
 */    

public void onClick(View v) 
{
    if (mLongClickStartsDrag) {
       // Tell the user that it takes a long click to start dragging.
       toast ("Manten presionada la ficha para desplazarla por la pantalla.");
    }
}

/**
 * Handle a click of Validar Respuesta
 *
 */

public void onClickValidar (View v)
{
    validarNumero ();
}

/**
 * Handle a click of Siguiente Numuero
 *
 */

public void onClickSiguiente (View v)
{
    obtenerSiguienteNumero ();
}

/**
 * Handle a click of the Add Image button
 *
 */    

public void onClickAddImage (View v) 
{
    addNewImageToScreen ();
}

/**
 * onCreate - called when the activity is first created.
 * 
 * Creates a drag controller and sets up three views so click and long click on the views are sent to this activity.
 * The onLongClick method starts a drag sequence.
 *
 */

 protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.demo);
     ADX = new AlertDialog.Builder(this);
     AD = ADX.create();
     imgHolder1 = (FrameLayout) findViewById (R.id.image_source_frame_1);
     imgHolder2 = (FrameLayout) findViewById (R.id.image_source_frame_2);
     imgHolder3 = (FrameLayout) findViewById (R.id.image_source_frame_3);
     imgHolder4 = (FrameLayout) findViewById (R.id.image_source_frame_4);
     imgHolder5 = (FrameLayout) findViewById (R.id.image_source_frame_5);
     imgHolder6 = (FrameLayout) findViewById (R.id.image_source_frame_6);
     imgHolder7 = (FrameLayout) findViewById (R.id.image_source_frame_7);
     imgHolder8 = (FrameLayout) findViewById (R.id.image_source_frame_8);
     imgHolder9 = (FrameLayout) findViewById (R.id.image_source_frame_9);
     imgHolder10 = (FrameLayout) findViewById (R.id.image_source_frame_10);


     frames.add(imgHolder1);
     frames.add(imgHolder2);
     frames.add(imgHolder3);
     frames.add(imgHolder4);
     frames.add(imgHolder5);
     frames.add(imgHolder6);
     frames.add(imgHolder7);
     frames.add(imgHolder8);
     frames.add(imgHolder9);
     frames.add(imgHolder10);

     /*long numb = 110110111;
     int decimal = convertBinaryToDecimal(numb);
     System.out.printf("%d in binary = %d in decimal", numb, decimal);*/

     TV_Numero = (TextView) findViewById(R.id.Numero);
     BT_Siguiente = (Button) findViewById(R.id.Siguiente);
     BT_Salir = (Button) findViewById(R.id.Salir);
     
     // When a drag starts, we vibrate so the user gets some feedback.
     mVibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

     BT_Salir.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //Intent intent = new Intent(getApplicationContext(), Menu.class);
             //startActivity(intent);
             toast("Salir al menú.");
         }
     });
     
     // Cambia el valor del textView del número a adivinar
     obtenerSiguienteNumero();
 }

    /**
     * Esta función se encarga de validar la respuesta de las casillas en la pantalla
     * si es correcto mostrará un toast "¡Correcto!" de lo contrario dirá al usuario
     * que se ha equivocado.
     */
     public void validarNumero(){
         if(1==1){
             miAlertDialog("¡Correcta!");
         }else{
             miAlertDialog("¡Incorrecta!");
         }


     }


 public void miAlertDialog(String mensaje){
     AlertDialog.Builder builder = new AlertDialog.Builder(DragActivity.this);
     builder.setTitle("Su respuesta es:")
             .setMessage(mensaje)
             .setCancelable(false)
             .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {}
             })
             .setNegativeButton("Explicación", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     AD.setMessage("Aquí va la explicación del resultado.");
                     AD.show();
                 }
             });
     //Creating dialog box
     AlertDialog dialog  = builder.create();
     dialog.show();
 }

 public void obtenerSiguienteNumero(){
     // Obtiene el número random decimal que aparecerá en la pantalla.
     num = rand.nextInt((100 - 0) + 1) + 0;

     // Convierte el número al resultado binario esperado
     binary = convertDecimalToBinary(num);

     // Convierte el binario a string (para poder obtener la cantidad de cifras y poder dibujar las
     // casillas correspondientes en la pantalla.
     casillas = Long.toString(binary).length();

     // Escribe el número a adivinar en el


     // This activity will listen for drag-drop events.
     // The listener used is a DragController. Set it up.
     mDragController = new DragController (this);

     // Set up the grid view with an ImageCellAdapter and have it use the DragController.
     GridView gridView = (GridView) findViewById(R.id.image_grid_view);
     if (gridView == null) toast ("Unable to find GridView");
     else {
         gridView.setAdapter (new ImageCellAdapter (this, mDragController,casillas));
     }


     // Always add the delete_zone so there is a place to get rid of views.
     // Find the delete_zone and add it as a drop target.
     // That gives the user a place to drag views to get them off the screen.
     //mDeleteZone = (DeleteZone) findViewById (R.id.delete_zone_view);
     //if (mDeleteZone != null) mDeleteZone.setOnDragListener (mDragController);

     /*/ Give the user a little guidance.
     Toast.makeText (getApplicationContext(),
             getResources ().getString (R.string.instructions),
             Toast.LENGTH_LONG).show ();*/
     addNewImageToScreen();

 }

    public static int convertBinaryToDecimal(long num){
        int decimalNumber = 0, i = 0;
        long remainder;
        while (num != 0)
        {
            remainder = num % 10;
            num /= 10;
            decimalNumber += remainder * Math.pow(2, i);
            ++i;
        }
        return decimalNumber;
    }
//casillas
    public static long convertDecimalToBinary(int n){
        long binaryNumber = 0;
        int remainder, i = 1, step = 1;
        while (n!=0)
        {
            remainder = n % 2;
            n /= 2;
            binaryNumber += remainder * i;
            i *= 10;
        }
        return binaryNumber;
    }

/**
 * Build a menu for the activity.
 *
 */    

public boolean onCreateOptionsMenu (Menu menu) {
    super.onCreateOptionsMenu(menu);
    
    menu.add(0, HIDE_TRASHCAN_MENU_ID, 0, "Hide Trashcan").setShortcut('1', 'c');
    menu.add(0, SHOW_TRASHCAN_MENU_ID, 0, "Show Trashcan").setShortcut('2', 'c');
    menu.add(0, ADD_OBJECT_MENU_ID, 0, "Add View").setShortcut('9', 'z');
    menu.add (0, CHANGE_TOUCH_MODE_MENU_ID, 0, "Change Touch Mode");


    return true;
}

/**
 * Handle a click of an item in the grid of cells.
 * 
 */

public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
{
    ImageCell i = (ImageCell) v;
    trace ("onItemClick in view: " + i.mCellNumber);
}

/**
 * Handle a long click.
 * If mLongClickStartsDrag  only is true, this will be the only way to start a drag operation.
 *
 * @param v View
 * @return boolean - true indicates that the event was handled
 */    

public boolean onLongClick(View v) 
{
    if (mLongClickStartsDrag) {
       
        //trace ("onLongClick in view: " + v + " touchMode: " + v.isInTouchMode ());

        // Make sure the drag was started by a long press as opposed to a long click.
        // (Note: I got this from the Workspace object in the Android Launcher code. 
        //  I think it is here to ensure that the device is still in touch mode as we start the drag operation.)
        if (!v.isInTouchMode()) {
           toast ("isInTouchMode returned false. Try touching the view again.");
           return false;
        }
        return startDrag (v);
    }

    // If we get here, return false to indicate that we have not taken care of the event.
    return false;
}

/**
 * Handle a long click.
 * If mLongClick only is true, this will be the only way to start a drag operation.
 *
 * @param v View
 * @return boolean - true indicates that the event was handled
 */    

public boolean onLongClickOLD (View v) 
{
    if (mLongClickStartsDrag) {
       
        //trace ("onLongClick in view: " + v + " touchMode: " + v.isInTouchMode ());

        // Make sure the drag was started by a long press as opposed to a long click.
        // (Note: I got this from the Workspace object in the Android Launcher code. 
        //  I think it is here to ensure that the device is still in touch mode as we start the drag operation.)
        if (!v.isInTouchMode()) {
           toast ("isInTouchMode returned false. Try touching the view again.");
           return false;
        }
        return startDrag (v);
    }

    // If we get here, return false to indicate that we have not taken care of the event.
    return false;
}

/**
 * Perform an action in response to a menu item being clicked.
 *
 */

public boolean onOptionsItemSelected (MenuItem item) {
    switch (item.getItemId()) {
        case HIDE_TRASHCAN_MENU_ID:
            if (mDeleteZone != null) mDeleteZone.setVisibility (View.INVISIBLE);
            return true;
        case SHOW_TRASHCAN_MENU_ID:
            if (mDeleteZone != null) mDeleteZone.setVisibility (View.VISIBLE);
            return true;
        case ADD_OBJECT_MENU_ID:
            // Add a new object to the screen;
            addNewImageToScreen ();
            return true;
        case CHANGE_TOUCH_MODE_MENU_ID:
            mLongClickStartsDrag = !mLongClickStartsDrag;
            String message = mLongClickStartsDrag ? "Changed touch mode. Drag now starts on long touch (click)." 
                                                  : "Changed touch mode. Drag now starts on touch (click).";
            Toast.makeText (getApplicationContext(), message, Toast.LENGTH_LONG).show ();
            return true;
    }
    return super.onOptionsItemSelected(item);
}

/**
 * This is the starting point for a drag operation if mLongClickStartsDrag is false.
 * It looks for the down event that gets generated when a user touches the screen.
 * Only that initiates the drag-drop sequence.
 *
 */    

public boolean onTouch (View v, MotionEvent ev) {
    // If we are configured to start only on a long click, we are not going to handle any events here.
    if (mLongClickStartsDrag) return false;

    boolean handledHere = false;

    final int action = ev.getAction();

    // In the situation where a long click is not needed to initiate a drag, simply start on the down event.
    if (action == MotionEvent.ACTION_DOWN) {
       handledHere = startDrag (v);
    }
    
    return handledHere;
}   

/**
 * Start dragging a view.
 *
 */    

public boolean startDrag (View v) {
    // We are starting a drag-drop operation. 
    // Set up the view and let our controller handle it.
    v.setOnDragListener (mDragController);
    mDragController.startDrag (v);
    return true;
}

/**
 * Show a string on the screen via Toast.
 * 
 * @param msg String
 * @return void
 */

public void toast (String msg)
{
    Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
} // end toast

/**
 * Send a message to the debug log. Also display it using Toast if Debugging is true.
 */

public void trace (String msg) 
{
    Log.d (LOG_NAME, msg);
    if (Debugging) toast (msg);
}


/**
 */
// DragDropPresenter methods

/**
 * This method is called to determine if drag-drop is enabled.
 * 
 * @return boolean
 */

public boolean isDragDropEnabled () {
    return true;
}

/**
 * React to the start of a drag-drop operation.
 * In this activity, we vibrate to give the user some feedback.
 * 
 * @param source DragSource
 * @return void
 */

public void onDragStarted (DragSource source) {
    mVibrator.vibrate(VIBRATE_DURATION);
}

/**
 * This method is called on the completion of a drag operation.
 * If the drop was not successful, the target is null.
 * 
 * @param target DropTarget
 * @param success boolean - true means that the object was dropped successfully
 */

public void onDropCompleted (DropTarget target, boolean success) {
}

} // end class
