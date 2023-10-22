package dstu.kuvaiev.expensetracker.test

import org.junit.Before;
import org.junit.Test;
import dstu.kuvaiev.expensetracker.domain.Expense
import dstu.kuvaiev.expensetracker.domain.ExpenseTracker
import org.junit.Assert.assertEquals
import java.util.Date

class ExpenseTrackerTest {
    private lateinit var expenseTracker: ExpenseTracker

    @Before
    fun setUp() {
        expenseTracker = ExpenseTracker()
    }

    @Test
    fun testAddExpense() {
        // Додаємо одну витрату і перевіряємо, чи вона була додана успішно
        val expense = Expense(100.0, Date())
        expenseTracker.addExpense(expense)
        assertEquals(100.0, expenseTracker.calculateTotalExpenses(), 0.0)

        // Додаємо ще одну витрату і перевіряємо, чи правильно обчислюється загальна сума
        val expense2 = Expense(50.0, Date())
        expenseTracker.addExpense(expense2)
        assertEquals(150.0, expenseTracker.calculateTotalExpenses(), 0.0)
    }

    @Test
    fun testCalculateTotalExpenses() {
        // Перевіряємо, чи обчислюється загальна сума правильно
        val expense1 = Expense(50.0, Date())
        val expense2 = Expense(75.0, Date())
        val expense3 = Expense(30.0, Date())

        expenseTracker.addExpense(expense1)
        expenseTracker.addExpense(expense2)
        expenseTracker.addExpense(expense3)

        assertEquals(155.0, expenseTracker.calculateTotalExpenses(), 0.0)
    }

    @Test(expected = ExpenseAmountNegativeException::class)
    fun testAddNegativeExpense() {
        // Спроба додати витрату з від'ємною сумою, має викликати ExpenseAmountNegativeException
        val expense = Expense(-50.0, Date())
        expenseTracker.addExpense(expense)
    }

}
