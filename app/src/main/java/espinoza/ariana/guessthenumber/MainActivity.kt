package espinoza.ariana.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {
    var minValue=0
    var maxValue=100
    var num:Int=0
    var won=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instancia al objeto layout guessings
        val guessings: TextView =findViewById(R.id.guessings)
        val down: Button =findViewById(R.id.down)
        val up: Button =findViewById(R.id.up)
        val generate: Button =findViewById(R.id.generate)
        val guessed: Button =findViewById(R.id.guessed)

        //Metodo del clic del boton generate
        generate.setOnClickListener{
            num= Random().nextInt(maxValue-minValue)
            guessings.setText(num.toString())
            generate.visibility= View.INVISIBLE
            guessed.visibility= View.INVISIBLE
        }

        up.setOnClickListener{
            minValue=num
            if(checkingLimits()){
                num= Random().nextInt(maxValue-minValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("No puede ser, ganaste")
            }
        }

        down.setOnClickListener(){
            maxValue=num
            if(checkingLimits()){
                num= Random().nextInt(maxValue-minValue)
                guessings.setText("No puede ser, ganaste")
            }
        }

        guessed.setOnClickListener{
            if(!won){
                guessings.setText("Adivine, tu numero es el "+num)
                guessed.setText("Volver a jugar")
                won=true
            }else{
                generate.visibility=View.VISIBLE
                guessings.setText("Tap on generate to start")
                guessed.visibility=View.GONE
                resetValues()
            }
        }
    }

    fun resetValues(){
        minValue=0
        maxValue=0
        num=0
        won=false
    }

    fun checkingLimits(): Boolean{
        return minValue!=maxValue
    }
}