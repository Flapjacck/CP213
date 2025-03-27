package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-01-05
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    // your code here
	    try {
                // Trigger system print dialog
                java.awt.print.PrinterJob job = java.awt.print.PrinterJob.getPrinterJob();
                job.setPrintable(order);
                if (job.printDialog()) {
                    job.print(); // Actually print
                }
            } catch (Exception ex) {
                // Suppress error output (no JOptionPane)
                System.out.println("Printing failed: " + ex.getMessage());
            }

	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
	private MenuItem item = null;

	QuantityListener(final MenuItem item) {
	    this.item = item;
	}

	// your code here
	@Override
        public void focusGained(FocusEvent e) {
            // Nothing needed when focused
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField field = (JTextField) e.getSource();
            String input = field.getText();
            int quantity = 0;

            try {
                quantity = Integer.parseInt(input);
                if (quantity < 0) {
                    quantity = 0;
                }
            } catch (NumberFormatException ex) {
                quantity = 0;
            }

            // Update order and text field
            order.update(item, quantity);
            field.setText(String.valueOf(quantity));

            // Update totals
            subtotalLabel.setText(priceFormat.format(order.getSubTotal()));
            taxLabel.setText(priceFormat.format(order.getTaxes()));
            totalLabel.setText(priceFormat.format(order.getTotal()));
        }

    }

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final DecimalFormat priceFormat = new DecimalFormat("$##0.00");
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {

	// your code here
	GridLayout gLayout = new GridLayout(menu.size() + 5, 3);
	setLayout(gLayout);

	JLabel itemHeading = new JLabel("Item");
	JLabel priceHeading = new JLabel("Price");
	JLabel quantityHeading = new JLabel("Quantity");

	this.add(itemHeading);
	this.add(priceHeading);
	this.add(quantityHeading);

	for (int i = 0; i < menu.size(); i++) {
	    MenuItem item = menu.getItem(i);
	    nameLabels[i] = new JLabel(item.getEntity());
	    priceLabels[i] = new JLabel(item.getAmount().toString());
	    quantityFields[i] = new JTextField("0", 5);

	    this.add(nameLabels[i]);
	    this.add(priceLabels[i]);
	    this.add(quantityFields[i]);

	}

	JLabel subtotalHeading = new JLabel("Subtotal:");
	JLabel taxHeading = new JLabel("Tax:");
	JLabel totalHeading = new JLabel("Total:");

	this.add(subtotalHeading);
	this.add(new JLabel(""));
	this.add(subtotalLabel);
	this.add(taxHeading);
	this.add(new JLabel(""));
	this.add(taxLabel);
	this.add(totalHeading);
	this.add(new JLabel(""));
	this.add(totalLabel);
	this.add(new JLabel(""));
	this.add(printButton);

	this.setVisible(true);


    }

    /**
     * Register the widget listeners with the widgets.
     */
    private void registerListeners() {
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());

	// your code here
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());

	// your code here
	for (int i = 0; i < menu.size(); i++) {
	    MenuItem item = menu.getItem(i);
	    quantityFields[i].addFocusListener(new QuantityListener(item));
	}


    }
}