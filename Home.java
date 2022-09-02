package cw2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Home {
    public static void main(String[]args){
        Login log = new Login();
    }

}
class Login{
    Login(){
        JFrame f = new JFrame("Bibhuti Pustak Bhandar");
        JLabel cname,luser,lpsw;
        JTextField tfuser;
        JPasswordField tfpsw;
        JButton btnLogin,btnSign;

        cname=new JLabel("Bibhuti Pustak Bhandar");
        f.add(cname);

        luser=new JLabel("Username");
        f.add(luser);

        lpsw=new JLabel("Password");
        f.add(lpsw);

        tfuser=new JTextField(30);
        f.add(tfuser);

        tfpsw=new JPasswordField();
        f.add(tfpsw);

        btnLogin=new JButton("Login");
        f.add(btnLogin);

        btnSign=new JButton("Register here!");
        f.add(btnSign);

        cname.setBounds(630,100,200,45);
        luser.setBounds(550,200,100,25);
        lpsw.setBounds(550,250,100,25);
        tfuser.setBounds(620,200,180,25);
        tfpsw.setBounds(620,250,180,25);
        btnLogin.setBounds(650,350,100,25);
        btnSign.setBounds(635,390,130,25);

        btnSign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Signup();
                f.dispose();

            }
        });


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user= tfuser.getText();
                String psw = tfpsw.getText();
                Login ho = new Login();
                ho.auth(user,psw,f);

            }
        });

        f.setLayout(null);
        f.setSize(1920,1080);
        f.setVisible(true);

    }
    public int auth(String user, String psw, Frame f){
        String query1 = "Select * from users where username = '"+user+"' and password ='"+psw+"'";
        DbConnect db = new DbConnect();
        try {
            ResultSet logresult =db.connection().executeQuery(query1);


            if (logresult.isBeforeFirst()){
                JOptionPane.showMessageDialog(f, "Login success");

                f.dispose();
                MainApp ma = new MainApp();

                ma.main(null);
                return 1;
            }

            else if(user.isBlank() || psw.isBlank()){
                JOptionPane.showMessageDialog(f, "Please enter username or password");
                return -1;
            }

            else {
                JOptionPane.showMessageDialog(f, "Wrong credentials!!");
                f.dispose();
                new Login();
                return -1;


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;

    }

}
class Signup {
    Signup(){
        JFrame f = new JFrame("Signup");
        JLabel head,username,email,password,cpassword;
        JTextField tf_uname,tf_email, tf_psw, tf_cpsw;

        JButton login, signup;

        head=new JLabel("Fill up the form to register!");
        f.add(head);

        username=new JLabel("Username:");
        f.add(username);

        email=new JLabel("Email:");
        f.add(email);

        password=new JLabel("Password:");
        f.add(password);

        cpassword=new JLabel("Confirm Password:");
        f.add(cpassword);

        tf_uname=new JTextField(30);
        f.add(tf_uname);

        tf_email=new JTextField(50);
        f.add(tf_email);

        tf_psw=new JTextField(30);
        f.add(tf_psw);

        tf_cpsw=new JTextField(30);
        f.add(tf_cpsw);


        signup=new JButton("Register");
        f.add(signup);

        login=new JButton("Login here");
        f.add(login);

    head.setBounds(620,100,200,25);
        username.setBounds(550,200,100,25);
        email.setBounds(570,250,200,25);
        password.setBounds(550,300,100,25);
        cpassword.setBounds(500,350,150,30);

        tf_uname.setBounds(620,200,180,25);
        tf_email.setBounds(620,250,180,25);
        tf_psw.setBounds(620,300,180,25);
        tf_cpsw.setBounds(620,350,180,25);

        signup.setBounds(650,450,100,25);
        login.setBounds(635,510,130,25);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                f.dispose();
            }
        });

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tf_uname.getText();
                String email = tf_email.getText();

                String password = tf_psw.getText();
                String cpassword = tf_cpsw.getText();

                if (!(password.equals(cpassword))){
                    JOptionPane.showMessageDialog(f, "Password doesn't match!!");
                    f.dispose();
                    new Signup();
                }

//                else if(username or email .exists){}
                else{
                    try {
                        DbConnect db= new DbConnect();
                        String query="insert into users values ('"+username+"','"+email+"','"+password+"')";
                        Integer result =  db.connection().executeUpdate(query);
                        JOptionPane.showMessageDialog(f, "User registered successfully!");
                        f.dispose();
                        new Login();



                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }

//                System.out.println(name + code + position + salary);

                String query = "Select * from users";
                DbConnect db = new DbConnect();
                try {
                    ResultSet result =db.connection().executeQuery(query);

                    while (result.next()){
                        String uname = result.getString("username");
                        String uemail = result.getString("email");
                        String psw = result.getString("password");


                        System.out.println(uname  + psw + uemail );

                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });

        f.setLayout(null);
        f.setSize(1920,1080);
        f.setVisible(true);
    }


}




