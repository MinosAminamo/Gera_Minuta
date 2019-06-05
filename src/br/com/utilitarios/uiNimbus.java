package br.com.utilitarios;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class uiNimbus {
	public static void ui() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, fall back to cross-platform
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				// not worth my time
			}
		}
	}
}
