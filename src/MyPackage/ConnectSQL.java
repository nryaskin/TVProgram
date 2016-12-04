package MyPackage;


import java.awt.*;
import java.io.Console;
import java.sql.*;
import java.util.*;
import java.util.List;
//import java.util.concurrent.Executor;

/**
 * Created by Никита on 04.12.2016.
 */
public class ConnectSQL {

    public Connection GetConnect() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "12345");
        } catch (Exception se) {
            System.out.println("Error :" + se.toString());
        }
        return conn;
    }


    public List<Program> GetAll() {
        ArrayList<Program> pr = new ArrayList<>();
        ConnectSQL connect = new ConnectSQL();
        try {
            Connection conn = connect.GetConnect();
            PreparedStatement patm = conn.prepareStatement("Select * from \"Program\"");
            ResultSet rs = patm.executeQuery();
            while (rs.next()) {
                Program programs = new Program();
                programs.setChanel(rs.getString("channel"));
                programs.setWeekday(rs.getString("weekday"));
                programs.setStart_time(rs.getString("start_time"));
                programs.setGenre(rs.getString("genre"));
                pr.add(programs);
                System.out.println("chanel : " + rs.getString("channel")+"weekday : " + rs.getString("weekday")+"start_time : " + rs.getInt("start_time") + "\t\tgenre : " + rs.getString("genre"));
                /*System.out.println("weekday : " + rs.getString("weekday"));
                System.out.println("start_time : " + rs.getInt("start_time"));
                System.out.println("genre : " + rs.getString("genre"));*/
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.toString());
        }
        return pr;
    }

    public void Insert(int id, String chanel, String weekday, String start_time, String genre) {
        ConnectSQL connect = new ConnectSQL();
        Connection conn = null;
        try {
            conn = connect.GetConnect();
            PreparedStatement patm = conn.prepareStatement("Insert into \"Program\"(id, channel, weekday, start_time, genre) values(?, ?, ?, ?, ?)");
            patm.setInt(1,id);
            patm.setString(2, chanel);
            patm.setString(3, weekday);
            patm.setString(4, start_time);
            patm.setString(5, genre);
            int row = patm.executeUpdate();
            if (row > 0) {
                System.out.println("Новая запись успешно добавлена");
            } else {
                System.out.println("Запись с этим id уже существует, введите другое");
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.toString());
        }
        finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error :" + e.toString());
                }
        }
    }

    public void Update(String chanel, String weekday, String start_time, String genre, int id) {
        ConnectSQL connect = new ConnectSQL();
        Connection conn = null;
        try {
            conn = connect.GetConnect();
            PreparedStatement patm = conn.prepareStatement("Update \"Program\" Set channel = ?, weekday = ?, start_time = ?, genre = ? where id = ?");

            patm.setString(1, chanel);
            patm.setString(2, weekday);
            patm.setString(3, start_time);
            patm.setString(4, genre);
            patm.setInt(5, id);
            int row = patm.executeUpdate();
            if (row > 0) {
                System.out.println("Запись успешно обновлена");
            } else {
                System.out.println("False");
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.toString());
        }
        finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error :" + e.toString());
                }
        }
    }

    public void Delete(int id) {
        ConnectSQL connect = new ConnectSQL();
        Connection conn = null;
        try {
            conn = connect.GetConnect();
            PreparedStatement patm = conn.prepareStatement("Delete from \"Program\" where id = ?");
            patm.setInt(1, id);
            int row = patm.executeUpdate();
            if (row > 0) {
                System.out.println("Успешно удалено");
            } else {
                System.out.println("Запись с таким id не найдена");
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.toString());
        }
        finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error :" + e.toString());
                }
        }

    }

    public static void main(String args[]) {
        ConnectSQL connect = new ConnectSQL();
        Scanner in = new Scanner(System.in);

        System.out.print("Добавить новую запись : 0; Изменить : 1 ; Удалить : 2; Показать : 3 ; Выход : 4\n");
        while (true){
            switch (in.nextInt()){
                case  0:
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Введите id ");
                    int i=sc.nextInt();
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Введите название канала: ");
                    String s2 = sc1.nextLine();
                    System.out.println("Введите день недели: ");
                    String s3 = sc1.nextLine();
                    System.out.println("Введите время: ");
                    String s4 = sc1.nextLine();
                    System.out.println("Введите жанр: ");
                    String s5 = sc1.nextLine();
                    connect.Insert(i, s2, s3, s4, s5);
                        //connect.Insert(8,"ТНТ","Вторник","10.00","мультфильм");
                    break;
                case 1:
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Введите id ");
                    int i1=sc2.nextInt();
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Введите название канала: ");
                    String s22 = sc3.nextLine();
                    System.out.println("Введите день недели: ");
                    String s33 = sc3.nextLine();
                    System.out.println("Введите время: ");
                    String s44 = sc3.nextLine();
                    System.out.println("Введите жанр: ");
                    String s55 = sc3.nextLine();
                    connect.Update(s22, s33, s44, s55,i1);
                    //connect.Update("Культура","Понедельник","20.00","документальный",7);
                    break;
                case 2:
                    int id = in.nextInt();
                    connect.Delete(id);
                    break;
                case 3:
                    System.out.println(connect.GetAll());
                    break;
                case 4:
                    System.exit(0); break;
                default:
                    System.out.println("Введите другое число");
            }
            System.out.print("Добавить новую запись : 0; Изменить : 1 ; Удалить : 2; Показать : 3 ; Выход : 4\n");
        }
    }
}
