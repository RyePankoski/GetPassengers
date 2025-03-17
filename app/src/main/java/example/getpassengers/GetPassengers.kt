package example.getpassengers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class GetPassengers : AppCompatActivity() {
    var passList: MutableList<Passenger> = ArrayList<Passenger>()
    private lateinit var textFirst: EditText
    private lateinit var textLast: EditText
    private lateinit var textPhone: EditText
    private lateinit var textPut: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)

        textFirst = findViewById(R.id.first_name)
        textLast = findViewById(R.id.last_name)
        textPhone = findViewById(R.id.phone_number)
        textPut = findViewById(R.id.accum_list)
    }

    fun enterPassenger(v: View) {
        val firstName = textFirst.text.toString()
        val lastName = textLast.text.toString()
        val phoneNumber = textPhone.text.toString()

        if (firstName.isNotBlank() && lastName.isNotBlank() && phoneNumber.isNotBlank()) {
            val newPass = Passenger(firstName, lastName, phoneNumber)

            passList.add(newPass)

            textPut.append("\n${newPass}")

            textFirst.text.clear()
            textLast.text.clear()
            textPhone.text.clear()

            textFirst.requestFocus()
        }
    }

    fun backToMain(v: View) {
        // Create an intent to return data
        val intent = Intent()

        intent.putExtra("COUNT", passList.size.toString())

        for (i in 1..passList.size) {
            intent.putExtra("PASS$i", passList[i-1].toString())
        }

        setResult(RESULT_OK, intent)

        finish()
    }
}