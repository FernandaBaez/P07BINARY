package com.upvictoria.dispositivos_moviles_may_ago_2019.p07_molina_pastrana_ana_karen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
* Esta actividad presenta una pantalla con una cuadrícula en la que se pueden agregar y mover imágenes.
* También define áreas en la pantalla donde se pueden quitar las vistas arrastradas. La retroalimentación visual es
* proporcionado al usuario a medida que los objetos se arrastran sobre las vistas donde se puede soltar algo.
*
* <p>
* Este ejemplo comienza a arrastrarse cuando se toca una vista DragSource.
* Si desea comenzar con una pulsación larga (clic largo), establezca la variable mLongClickStartsDrag en verdadero.
*
*/

public class DragActivity2 extends Activity
    implements View.OnLongClickListener, View.OnClickListener,
               DragDropPresenter,
               View.OnTouchListener
{


// Constantes
public static final String LOG_NAME = "DragActivity2";
private Vibrator mVibrator;
private static final int VIBRATE_DURATION = 35;

public static final boolean Debugging = false;   // Use this to see extra toast messages while debugging.
public static String PACKAGE_NAME; // Nombre del paquete global

// Variables

private DragController2 mDragController;   // Obj que controla la secuencia del drag and drop
private int mImageCount = 0;              // Cantidad de casillas llenadas en la pantalla.
private ImageCell2 mLastNewCell = null;    // La última ImageCell agregada a la pantalla cuando se hace clic en Agregar imagen.
private boolean mLongClickStartsDrag = false;   // Si es verdadero, se necesita un largo clic para iniciar la operación de arrastre.
    // De lo contrario, cualquier evento táctil inicia un arrastre.

public Random rand = new Random(); // Variable random
public static String explicacion = "";
public  int num = 0; // Número en decimal
public  long binary = 0; // Número en binario
public  int casillas = 0; // Cantidad de casillas a mostrar
public  int resourceId1 = R.drawable.uno1; // Valor del drawable UNO
public  int resourceId2 = R.drawable.dos; // Valor del drawable dos
public  int resourceId3 = R.drawable.tres; // Valor del drawable tres
public  int resourceId4 = R.drawable.cuatro; // Valor del drawable cuatro
public  int resourceId5 = R.drawable.cinco; // Valor del drawable cinco
public  int resourceId6 = R.drawable.seis; // Valor del drawable seis
public  int resourceId7 = R.drawable.siete; // Valor del drawable siete
public  int resourceId8 = R.drawable.ocho; // Valor del drawable ocho
public  int resourceId9 = R.drawable.nueve; // Valor del drawable nueve
public  int resourceId10 = R.drawable.diez; // Valor del drawable cinco
public  int resourceId11 = R.drawable.once; // Valor del drawable seis
public  int resourceId12 = R.drawable.doce; // Valor del drawable siete
public  int resourceId13 = R.drawable.trece; // Valor del drawable ocho
public  int resourceId14 = R.drawable.catorce; // Valor del drawable nueve
public  int resourceId15 = R.drawable.quince; // Valor del drawable nueve
public static ArrayList<String> respuesta = new ArrayList<>(); // Arreglo de las respuestas dadas por el usuario

// Layout de las casillas donde aparecerán las fichas para arrastrar.
FrameLayout imgHolder1,imgHolder2,imgHolder3,imgHolder4,imgHolder5,imgHolder6,imgHolder7,imgHolder8,imgHolder9,imgHolder10;
ArrayList<FrameLayout> frames =new ArrayList<>(); // Arraylist de los frames de las casillas

TextView TV_Numero; // TextView de número a calcular
Button BT_Salir; // Botón de salir al menú
Button BT_Siguiente; // Botón para ver siguiente número
AlertDialog.Builder ADX; // Alert Builder
AlertDialog AD; // ALert Dialog

/**
 * Metodos
 */

/**
  * Agrega una nueva imagen para que el usuario pueda moverla. Se muestra en el image_source_frame
  * Parte de la pantalla.
    *
    * @param resourceId int: el ID de recurso de la imagen que se agregará
  **/

public void addNewImageToScreen (int resourceId, FrameLayout imgHolder) {

    if (imgHolder != null) {
       FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams (LayoutParams.MATCH_PARENT,
                                                                   LayoutParams.MATCH_PARENT,
                                                                   Gravity.CENTER);
       ImageCell2 newView = new ImageCell2 (this);
       newView.setImageResource (resourceId);
       imgHolder.addView (newView, lp);
       newView.mEmpty = false;
       newView.mCellNumber = -1;
       mLastNewCell = newView;
       mImageCount++;

       // Coloca los listener para las vistas generadas.
       newView.setOnClickListener(this);
       newView.setOnLongClickListener(this);
       newView.setOnTouchListener (this);

    }
}

/**
* Agregue una de las imágenes a la pantalla para que el usuario tenga una nueva imagen para moverse.
* Ver addImageToScreen.
*/
public void addNewImageToScreen () {
    // Recorre el arreglo de frames para quitar las fichas que hayan quedado y reiniciarlas
    for(int i=0;i<1;i++){ frames.get(i).removeAllViews(); }

    // Convierte long binary a string
    //String str_bin = Long.toString(num);
    int str_num = num;
    // Recorre las casillas, dependiendo del resultado se envian las fichas de 0 o 1 además de la respuesta
    // al número generado aleatoriamente.
    for(int i=0;i<4;i++) {
        if (str_num == 1) { // Si es uno
            addNewImageToScreen(resourceId1, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 2){ // Si es uno
            addNewImageToScreen(resourceId2, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 3){ // Si es uno
            addNewImageToScreen(resourceId3, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 4){ // Si es uno
            addNewImageToScreen(resourceId4, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 5){ // Si es uno
            addNewImageToScreen(resourceId5, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 6){ // Si es uno
            addNewImageToScreen(resourceId6, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 7){ // Si es uno
            addNewImageToScreen(resourceId7, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 8){ // Si es uno
            addNewImageToScreen(resourceId8, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 9){ // Si es uno
            addNewImageToScreen(resourceId9, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 10){ // Si es uno
            addNewImageToScreen(resourceId10, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 11){ // Si es uno
            addNewImageToScreen(resourceId11, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 12){ // Si es uno
            addNewImageToScreen(resourceId12, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 13){ // Si es uno
            addNewImageToScreen(resourceId13, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        } else if (str_num == 14){ // Si es uno
            addNewImageToScreen(resourceId14, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        }  else if (str_num == 15){ // Si es uno
            addNewImageToScreen(resourceId15, frames.get(i));
            str_num = rand.nextInt((15 - 0) + 1) + 0;
        }


    }
}

/**
 * Controla simple click sobre la ficha.
 *
 */    

public void onClick(View v)
{
    if (mLongClickStartsDrag) {
       // Aviso de uso para el usuario.
       toast ("Manten presionada la ficha para desplazarla por la pantalla.");
    }
}

/**
 * Controla click de Validar Respuesta
 *
 */

public void onClickValidar (View v) { validarNumero (); }

/**
 * Controla click de Ayuda
 *
 */

public void onClickAyuda (View v) {
    AD.setMessage("--- ¡BIENVENIDO! ---\n\nEn la parte superior de la pantalla aparece el número que tiene que convertirse a" +
            " binario, en la mitad inferior de la pantalla están las fichas con las cuales formarás el " +
            "resultado acomodándolas en las casillas vacías de la pantalla, el botón Validar te dirá si " +
            "tu respuesta fue correcta, por último, el botón Siguiente Número te mostrará otro número decimal" +
            " para continuar con la práctica.");
    AD.show();
}

/**
 * Controla click de Add Image button
 *
 */    

public void onClickAddImage (View v) { addNewImageToScreen (); }

/**
 * onCreate: se llama cuando la actividad se crea por primera vez.
 *
 * Crea un controlador de arrastre y configura tres vistas, de modo que haga clic y haga clic en las vistas que se envían a esta actividad.
 * El método onLongClick inicia una secuencia de arrastre.
 *
 */

 protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.demo2);

    // Genera las variables para el uso de alertdialog
     ADX = new AlertDialog.Builder(this);
     AD = ADX.create();

     // Inicaliza el nombre del paquete
     PACKAGE_NAME = getApplicationContext().getPackageName();

     // Inicializa el arreglo de respuestas con valores vacios
     for(int i = 0;i<2;i++){ respuesta.add(""); }

     // Inicializa los valores de los layout con su respectivo source
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

    // Registra los holder en el arreglo de frames
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
     BT_Siguiente = (Button) findViewById(R.id.Validar);
     BT_Salir = (Button) findViewById(R.id.Salir);

     // Cuando el drag comienza da una señal al usuario
     mVibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

     // Listener del botón para salir al menú principal.
     BT_Salir.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //Intent intent = new Intent(getApplicationContext(), Menu.class);
             //startActivity(intent);
             toast("Salir al menú.");
         }
     });
     AD.setMessage("--- ¡BIENVENIDO! ---\n\nEn la parte superior de la pantalla aparece el número que tiene que convertirse a" +
             " binario, en la mitad inferior de la pantalla están las fichas con las cuales formarás el " +
             "resultado acomodándolas en las casillas vacías de la pantalla, el botón Validar te dirá si " +
             "tu respuesta fue correcta, por último, el botón Siguiente Número te mostrará otro número decimal" +
             " para continuar con la práctica.");
     AD.show();
     // Cambia el valor del textView del número a adivinar
     obtenerSiguienteNumero();
 }

    /**
     * Esta función se encarga de validar la respuesta de las casillas en la pantalla
     * si es correcto mostrará un toast "¡Correcto!" de lo contrario dirá al usuario
     * que se ha equivocado.
     */
     public void validarNumero(){
         String str_respuesta = "";
         for (int i=0;i<casillas;i++){ str_respuesta+=respuesta.get(i); }

         // Realiza la comparación entre lo correcto y la respuesta del jugador
         if(str_respuesta.equals(Long.toString(binary))){
             miAlertDialog("¡Correcta!\n"+binary+"="+str_respuesta);

         }else{
             miAlertDialog("¡Incorrecta!\n"+binary+"="+str_respuesta);
         }

     }

    /**
     * Esta función genera un alertdialog con dos opciones, Cerrar y ver la Explicación del resultado
     * que se obtuvo.
     * @param mensaje Es el mensaje enviado por la aplicación al usuario para dar opciones.
     */
 public void miAlertDialog(String mensaje){
     AlertDialog.Builder builder = new AlertDialog.Builder(DragActivity2.this);
     builder.setTitle("Su respuesta es:")
             .setMessage(mensaje)
             .setCancelable(false)
             .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) { obtenerSiguienteNumero ();}
             })
             .setNegativeButton("Explicación", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     AD.setMessage("" + explicacion);
                     AD.show();
                     obtenerSiguienteNumero ();
                 }
             });
     //Crea la caja de dialogo
     AlertDialog dialog  = builder.create();
     dialog.show();
 }

 public void obtenerSiguienteNumero(){
     // Obtiene el número random decimal que aparecerá en la pantalla.
     num = rand.nextInt((15 - 0) + 1) + 0;
     // Convierte el número al resultado binario esperado
     binary = convertDecimalToBinary(num);

     // Convierte el binario a string (para poder obtener la cantidad de cifras y poder dibujar las
     // casillas correspondientes en la pantalla.
     casillas = Long.toString(binary).length();

     // Escribe el número a adivinar en el textview
     TV_Numero.setText("" + binary);

     // Esta actividad escuchará eventos de arrastrar y soltar.
     // El oyente utilizado es un DragController. Prepararlo.
     mDragController = new DragController2 (this);

     // Configure la vista de cuadrícula con un ImageCellAdapter y haga que use el DragController.
     GridView gridView = (GridView) findViewById(R.id.image_grid_view);
     if (gridView == null) toast ("No se encuentra GridView");
     else {
         gridView.setAdapter (new ImageCellAdapter (this, mDragController,1));
     }

     addNewImageToScreen();

 }

    public static int convertBinaryToDecimal(long num){
        explicacion = "Empieza por obtener el modulo de " + num + " entre 10 ";
        int decimalNumber = 0, i = 0;
        long remainder;
        while (num != 0) {
            remainder = num % 10;
            explicacion+="\nSe guarda el sobrante: " + remainder;
            num /= 10;
            explicacion+="\nSe divide el número entre 10: " + num;
            decimalNumber += remainder * Math.pow(2, i);
            explicacion+="\nAl resultado se suma el sobrante ( " + remainder + ") por el exponencial del número de iteraciones (" + i + ")";
            ++i;
        }
        explicacion+="\n\nRESULTADO: " + decimalNumber;
        return decimalNumber;
    }

    /**
     * Esta función recibe un numero entero y lo convierte a binario, como salida regresa el valor
     * binario del numero recibido.
     * @param n Es el número que será convertido.
     * */
    public static long convertDecimalToBinary(int n){
        long binaryNumber = 0;
        explicacion = "Empieza por inicializar el resto y el paso en 1 ";
        int remainder, i = 1, step = 1;
        while (n!=0)
        {
            explicacion += "\nSe sigue por obtener el modulo de " + n + " entre 2 ";
            remainder = n % 2;
            n /= 2;
            explicacion+="\nSe divide el número entre 2: " + n;
            binaryNumber += remainder * i;
            explicacion+="\nAl resultado se suma el sobrante ( " + remainder + ") por el número de iteraciones (" + i + ")";
            i *= 10;
            explicacion+="\nSe multiplica el número de iteraciones de la forma i*(i*10) = " + i;

        }
        explicacion+="\n\nRESULTADO: " + binaryNumber;
        return binaryNumber;
    }


/**
 * Controla click de los items en las celdas
 */

public void onItemClick(AdapterView<?> parent, View v, int position, long id)
{
    ImageCell2 i = (ImageCell2) v;
    trace ("onItemClick in view: " + i.mCellNumber);
}

/**
  * Manejar un clic largo.
  * Si mLongClickStartsDrag solo es verdadero, esta será la única forma de iniciar una operación de arrastre.
  * @param v View
  * @return boolean - true indica que el evento fue manejado
  */
public boolean onLongClick(View v)
{
    if (mLongClickStartsDrag) {

        // Asegúrese de que el arrastre se inició con una pulsación larga en lugar de un clic largo.
        // (Nota: Lo obtuve del objeto Workspace en el código de Android Launcher.
        // Creo que está aquí para garantizar que el dispositivo aún esté en modo táctil al iniciar la operación de arrastre.)
        if (!v.isInTouchMode()) {
           return false;
        }
        return startDrag (v);
    }

    return false;
}

/**
 * Manejar un clic largo.
 * Si mLongClick solo es verdadero, esta será la única forma de iniciar una operación de arrastre.
 *
 * @param v View
 * @return boolean - true indica que el evento fue manejado
 */    

public boolean onLongClickOLD (View v)
{
    if (mLongClickStartsDrag) {
        // Asegúrese de que el arrastre se inició con una pulsación larga en lugar de un clic largo.
        // (Nota: Lo obtuve del objeto Workspace en el código de Android Launcher.
        // Creo que está aquí para garantizar que el dispositivo aún esté en modo táctil al iniciar la operación de arrastre.)
        if (!v.isInTouchMode()) {
           return false;
        }
        return startDrag (v);
    }
    return false;
}

/**
 * Este es el punto de partida para una operación de arrastre si mLongClickStartsDrag es falso.
 * Busca el evento down que se genera cuando un usuario toca la pantalla.
 * Solo eso inicia la secuencia de arrastrar y soltar.
 *
 */    

public boolean onTouch (View v, MotionEvent ev) {
// Si estamos configurados para comenzar solo con un clic largo, no vamos a manejar ningún evento aquí.
    if (mLongClickStartsDrag) return false;

    boolean handledHere = false;

    final int action = ev.getAction();

// En la situación en la que no se necesita un clic prolongado para iniciar un arrastre, simplemente comience en el evento hacia abajo.
    if (action == MotionEvent.ACTION_DOWN) {
       handledHere = startDrag (v);
    }
    
    return handledHere;
}   

/**
 * Empieza a arrastrar una ficha
 *
 */    

public boolean startDrag (View v) {
    v.setOnDragListener (mDragController);
    mDragController.startDrag (v);
    return true;
}

/**
 * Función que agiliza el toast
 * 
 * @param msg String
 * @return void
 */

public void toast (String msg) {
    Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
} // end toast

/**
 * Envia un mensaje de depuración para la aplicación
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
 * Se llama a este método para determinar si la función de arrastrar y soltar está habilitada.
 * 
 * @return boolean
 */

public boolean isDragDropEnabled () {
    return true;
}

/**
 * Reaccionar al inicio de una operación de arrastrar y soltar.
 * En esta actividad, vibramos para dar al usuario algunos comentarios.
 *
 * @param source DragSource
 * @return void
 */

public void onDragStarted (DragSource source) {
    mVibrator.vibrate(VIBRATE_DURATION);
}

/**
 * Este método se llama en la finalización de una operación de arrastre.
 * Si la caída no tuvo éxito, el objetivo es nulo.
 * 
 * @param target DropTarget
 * @param success boolean - true means that the object was dropped successfully
 */

public void onDropCompleted (DropTarget target, boolean success) {}

} // end class
