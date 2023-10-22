package dstu.kuvaiev.expensetracker.domain

import java.util.Date

class Expense(val amount: Double, val date: Date) {
    init {
        require(amount > 0) { "Сума витрати повинна бути більше 0" }
    }
}
