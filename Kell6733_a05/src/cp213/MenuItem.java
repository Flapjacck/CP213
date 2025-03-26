package cp213;

import java.math.BigDecimal;

/**
 * Defines the entity and amount of a menu item. Price is stored as a BigDecimal
 * to avoid rounding errors.
 *
 * @author your entity here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-01-05
 */
public class MenuItem {

    // Attributes
    private static final String itemFormat = "%-12s $%5.2f";
    private String entity = null;
    private BigDecimal amount = null;

    /**
     * Constructor. Must set amount to 2 decimal points for calculations.
     *
     * @param entity Listing of the menu item.
     * @param amount Price of the menu item.
     */
    public MenuItem(final String entity, final BigDecimal amount) {

	// your code here
	this.entity = entity;
        
        this.amount = amount;


    }

    /**
     * Alternate constructor. Converts a double amount to BigDecimal.
     *
     * @param entity Listing of the menu item.
     * @param amount Price of the menu item.
     */
    public MenuItem(final String entity, final double amount) {

	// your code here
	this(entity, BigDecimal.valueOf(amount));

    }

    /**
     * entity getter
     *
     * @return Listing of the menu item.
     */
    public String getEntity() {
	return this.entity;
    }

    /**
     * amount getter
     *
     * @return Price of the menu item.
     */
    public BigDecimal getAmount() {
	return this.amount;
    }

    /**
     * Returns a MenuItem as a String in the format:
     *
     * <pre>
    hot dog      $ 1.25
    pizza        $10.00
     * </pre>
     */
    @Override
    public String toString() {

	// your code here

	return String.format(itemFormat, this.entity, this.amount);
    }
}