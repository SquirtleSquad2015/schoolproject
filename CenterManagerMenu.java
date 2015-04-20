/**
 * Created by Haldor on 4/20/2015.
 */

/* 
public class CenterManagerMenu extends JFrame {
	private final String username;

	JButton viewShopCenterInfoBtn = new JButton("View center information");
	JButton viewAdminInfoBtn = new JButton("View administrative information");
	JButton updateCenterInfoBtn = new JButton("Update center information");
	JButton establishNewShopBtn = new JButton("Establish new shop");
	JButton updateAccountInfoBtn = new JButton("Update account information");
	JButton manageUsersBtn = new JButton("Manage Users");
	JButton exitBtn = new JButton("Exit");


	// Administrative info Frame
	JFrame viewAdminFrame = new JFrame();
	JLabel turnoverLbl = new JLabel("Turnover", JLabel.CENTER);
	JLabel showTurnoverBtn = new JLabel("A milli", JLabel.CENTER);
	JButton backBtn = new JButton("Back");

	//Update center info frame
	JFrame updateCenterFrame = new JFrame();
	JButton updateCenterNameBtn = new JButton("Update center name");
	JButton updateCenterAreaBtn = new JButton("Update center area");
	JButton updateCenterNrOfShopsBtn = new JButton("Update nr of shops");
	JButton updateCarParkBtn = new JButton("Update car park");
	JButton updateCustomerServiceBtn = new JButton("Update customerservice info");
	JButton updateShopInfoBtn = new JButton("Update shop information");

	// Establish new shop frame

	// Update Account info frame

	//Manage users frame
	JFrame manageUsersFrame = new JFrame();
	JButton validateAccountBtn = new JButton("Validate Accounts");
	JButton changeAccesslevelBtn = new JButton("Change access levels");


	public CenterManagerMenu(String username){
		super("Center Manager -" + username);
		this.username = username;
		LayoutManager layout = new GridLayout(3,3,2,2);
		setLayout(layout);
		add(viewShopCenterInfoBtn);
		add(viewAdminInfoBtn);
		add(updateCenterInfoBtn);
		add(establishNewShopBtn);
		add(updateAccountInfoBtn);
		add(manageUsersBtn);
		add(exitBtn);
		pack();

		Action action = new Action();
		viewShopCenterInfoBtn.addActionListener(action);
		viewAdminInfoBtn.addActionListener(action);
		updateCenterInfoBtn.addActionListener(action);
		establishNewShopBtn.addActionListener(action);
		updateAccountInfoBtn.addActionListener(action);
		manageUsersBtn.addActionListener(action);
		exitBtn.addActionListener(action);

	}

	private class Action extends DatabaseConnection implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent source) {
			JButton check = (JButton) source.getSource();

			if (check == viewShopCenterInfoBtn) {
				shoolprodject.Customer CostumerVindu = new shoolprodject.Customer();
				CostumerVindu.setLocationRelativeTo(null);
				CostumerVindu.setVisible(true);
			} else if (check == viewAdminInfoBtn) {

			} else if (check == updateCenterInfoBtn) {

			} else if (check == establishNewShopBtn) {

			} else if (check == updateAccountInfoBtn) {
				try {
					openConnection();
					String email = getEmail(username);
					String tlf = getPhoneNumber(username);
					currentEmail.setText(email);
					currentPhoneNumber.setText(tlf);
					closeConnection();
				} catch (Exception e) {
					Database.printMesssage(e, "changeAccountInfo");
				}
				setVisible(false);
				changeAccountInfoFrame.setVisible(true);
				changeAccountInfoFrame.setLocationRelativeTo(null);
			} else if (check == manageUsersBtn) {

			} else if (check == exitBtn) {
				System.exit(0);
			}
		}
	}
}
*/
