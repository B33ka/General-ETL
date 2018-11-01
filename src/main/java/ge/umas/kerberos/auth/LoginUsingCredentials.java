package ge.umas.kerberos.auth;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sun.tools.javac.util.Pair;

public class LoginUsingCredentials {
	Pair<String, char[]> loginCreds = new Pair<String, char[]>(null, null);
	public void getUserCreds() {
		/* --------------------- */

		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("UserName", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField username = new JTextField();
		controls.add(username);
		JPasswordField password = new JPasswordField();
		controls.add(password);
		panel.add(controls, BorderLayout.CENTER);

		String[] options = new String[] { "OK", "Cancel" };
		int option = JOptionPane.showOptionDialog(null, panel, "Login", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		if (option == 0) // pressing OK button
		{

			loginCreds = new Pair<String, char[]>(username.getText(), password.getPassword());
		}

		/* --------------------- */

	}

	public LoginContext kinit() throws LoginException {
		File confLoc = null;
		try {
			confLoc = new File(LoginUsingCredentials.class.getResource("/jaas-config.conf").toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		System.setProperty("java.security.auth.login.config", confLoc.getPath());
		//getUserCreds();
		/*final String username = loginCreds.fst;
		final char[] pass = loginCreds.snd;*/
		final String username ="b.saldadze";
		final char[] pass = "Iphone4gs10".toCharArray();
		LoginContext lc = new LoginContext("HdfsMain", new CallbackHandler() {
			public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
				for (Callback c : callbacks) {
					if (c instanceof NameCallback)
						((NameCallback) c).setName(username);
					if (c instanceof PasswordCallback)
						((PasswordCallback) c).setPassword(pass);
				}
			}
		});
		lc.login();
		return lc;
	}

}

