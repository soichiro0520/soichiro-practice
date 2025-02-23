package com.example.plugin.Initializers;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

import com.example.plugin.Plugin;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class CommandInit {

	public static void init() {
		try {
			ClassPath cp = ClassPath.from(CommandInit.class.getClassLoader());
			for (ClassInfo classInfo : cp.getTopLevelClassesRecursive("com.example.plugin.Commands.Commands")) {
				Class<?> clazz = Class.forName(classInfo.getName());
				try {
					if (CommandExecutor.class.isAssignableFrom(clazz)) {
						CommandExecutor command = (CommandExecutor) clazz.getDeclaredConstructor().newInstance();
						String commandName = classInfo.getSimpleName().replace("Command", "");
						Plugin.plugin.getCommand(commandName).setExecutor(command);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for (ClassInfo classInfo : cp.getTopLevelClassesRecursive("com.example.plugin.Commands.TabCompleters")) {
				Class<?> clazz = Class.forName(classInfo.getName());
				try {
					if (TabCompleter.class.isAssignableFrom(clazz)) {
						TabCompleter tabCompleter = (TabCompleter) clazz.getDeclaredConstructor().newInstance();
						String commandName = classInfo.getSimpleName().replace("TabCompleter", "");
						Plugin.plugin.getCommand(commandName).setTabCompleter(tabCompleter);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}