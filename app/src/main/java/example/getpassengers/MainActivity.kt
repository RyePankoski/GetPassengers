package example.getpassengers

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var listText: TextView
    private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listText = findViewById(R.id.show_list)

        startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val data = activityResult.data

                val count = (data?.getStringExtra("COUNT") ?: "0").toInt()

                listText.text = "RETURNED PASSENGER LIST\n-----------------------------\n"

                for (i in 1..count) {
                    val passenger = data?.getStringExtra("PASS$i") ?: ""
                    listText.append("$passenger\n")
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun getList(v: View) {
        // Clear the list display
        listText.text = "RETURNED PASSENGER LIST\n-----------------------------\n"

        // Create intent and launch the second activity
        val intent = Intent(this, GetPassengers::class.java)
        startForResult.launch(intent)
    }
}

class Passenger(val fName: String, val lName: String, val phone: String) {
    override fun toString(): String {
        return "$fName $lName $phone"
    }
}