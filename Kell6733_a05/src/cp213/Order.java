package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-01-05
 */
public class Order implements Printable {

    private static final String lineFormat = "%-14s%2d @ $%5.2f = $%6.2f\n";
    private static final String totalFormat = "%-9s                   $%6.2f\n";
    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);

    // define a Map of MenuItem objects
    // Note that this must be a *Map* of some flavour
    // @See
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Map.html

    // your code here
    private final Map<MenuItem, Integer> items = new HashMap<>();
    
    /**
     * Increments the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(final MenuItem item, final int quantity) {

	// your code here
	if (quantity > 0) {
            int current = items.getOrDefault(item, 0);
            items.put(item, current + quantity);
        }

    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total cost for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {

	// your code here
	BigDecimal subtotal = BigDecimal.ZERO;

        // Loop over each entry in the order
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            BigDecimal price = entry.getKey().getAmount();  // Price of item
            int quantity = entry.getValue();                // Quantity ordered

            // Add price * quantity to subtotal
            subtotal = subtotal.add(price.multiply(BigDecimal.valueOf(quantity)));
        }

        // Set to 2 decimal places
        return subtotal;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {

	// your code here
	BigDecimal taxes = getSubTotal().multiply(TAX_RATE);
        return taxes;
    }

    /**
     * Calculates and returns the total cost of all MenuItems order, including tax.
     *
     * @return total cost
     */
    public BigDecimal getTotal() {

	// your code here
	BigDecimal total = getSubTotal().add(getTaxes());
	return total;
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
	    throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    final Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    // Now we perform our rendering
	    final String[] lines = this.toString().split("\n");
	    int y = 100;
	    final int inc = 12;

	    for (final String line : lines) {
		g2d.drawString(line, 100, y);
		y += inc;
	    }
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {

	// your code here
	StringBuilder sb = new StringBuilder();

        // Print each item in the order
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            BigDecimal price = item.getAmount();
            BigDecimal lineTotal = price.multiply(BigDecimal.valueOf(quantity));

            sb.append(String.format(lineFormat, item.getEntity(), quantity, price, lineTotal));
        }

        // Add subtotal, tax, and total lines
        sb.append("\n");
        sb.append(String.format(totalFormat, "Subtotal:", getSubTotal()));
        sb.append(String.format(totalFormat, "Taxes:", getTaxes()));
        sb.append(String.format(totalFormat, "Total:", getTotal()));

        return sb.toString();
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item     The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(final MenuItem item, final int quantity) {

	// your code here
	if (quantity <= 0) {
            items.remove(item);
        } else {
            items.put(item, quantity);
        }

    }
}