package dstu.kuvaiev.expensetracker.domain

import dstu.kuvaiev.expensetracker.test.ExpenseAmountNegativeException

class ExpenseTracker {
    private val expenses = mutableListOf<Expense>()

    fun addExpense(expense: Expense) {
        if (expense.amount < 0) {
            throw ExpenseAmountNegativeException("Сума витрати не може бути від'ємною")
        }
        expenses.add(expense)
    }

    fun calculateTotalExpenses(): Double {
        return expenses.sumOf { it.amount }
    }

}

