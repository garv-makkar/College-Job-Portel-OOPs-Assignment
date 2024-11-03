import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Cred {
    private int _id;
    private String _name;

    public String getname() {
        return _name;
    }

    public int getid() {
        return _id;
    }
}

class Admin extends Cred {

    Scanner sc = new Scanner(System.in);

    private String username;
    private String password;

    @Override
    public String getname() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void EnterAsAdmin() {

        System.out.println("Please enter your username and password");
        System.out.println("Username: ");
        String n = sc.next();
        System.out.println("Password: ");
        String p = sc.next();
        if (n.equals("garv") && p.equals("lm10")) {
            Admin a = new Admin(n, p);
            Assignment2.AdminFunctionalities(a);
        } else {
            System.out.println("Invalid credentials, want to try again (y/n)?");
            String x = sc.next();
            if (x.equals("y")) {
                EnterAsAdmin();
            } else {
                Assignment2.FLIPZON();
            }
        }
    }

    public void AddCategory() {

        System.out.println("How many categories ypu want to add?");
        int t = sc.nextInt();
        for (int k = 0; k < t; k++) {
            System.out.println("Category " + String.valueOf(k + 1));
            System.out.println("Enter category ID to add new category: ");
            int id = sc.nextInt();
            System.out.println("Enter name of the new category you want to add: ");
            String categoryname = sc.next();

            Category cat = new Category(id, categoryname);
            Assignment2.ListOfCategories.add(cat);

            System.out.println("Number of products you want to add in this category: ");
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                System.out.println("Product " + String.valueOf(i + 1));
                System.out.println("Product ID: ");
                double pid = sc.nextDouble();
                System.out.println("Product name: ");
                String pname = sc.next();
                System.out.println("Product price: ");
                double pr = sc.nextDouble();
                System.out.println("Product stock: ");
                int q = sc.nextInt();
                Product p = new Product(pid, pname);
                p.price = pr;
                p.stock = q;
                cat.listOfProducts.add(p);
            }
            System.out.println("Category successfully added");
        }
    }

    public void DeleteCategory() {

        System.out.println("Enter details to delete the category: ");
        System.out.println("Category id: ");
        int cid = sc.nextInt();
        System.out.println("Category name: ");
        String name = sc.next();

        for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
            Category cat = Assignment2.ListOfCategories.get(i);
            if ((cat.getid() == cid) && (cat.getname().equals(name))) {
                Assignment2.ListOfCategories.remove(cat);
                System.out.println("Category removed");
            }
        }
    }

    public void AddProduct() {

        System.out.println("Enter Category ID to add product: ");
        int catId = sc.nextInt();
        System.out.println("Enter category name: ");
        String catname = sc.next();
        System.out.println("Enter id of product you want to add: ");
        double pid = sc.nextDouble();
        System.out.println("Enter name of Product you want to add: ");
        String pname = sc.next();
        System.out.println("Enter price of Product you want to add: ");
        double pprice = sc.nextDouble();
        System.out.println("Product stock: ");
        int q = sc.nextInt();

        Product p = new Product(pid, pname);
        p.price = pprice;
        p.stock = q;

        int z = 1;
        for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
            if (Assignment2.ListOfCategories.get(i).getid() == catId) {
                z = 0;
                Assignment2.ListOfCategories.get(i).listOfProducts.add(p);
                System.out.println("Product added successfully...");
            }
        }
        if (z == 1) {
            Category cnew = new Category(catId, catname);
            Assignment2.ListOfCategories.add(cnew);
            cnew.listOfProducts.add(p);
            System.out.println("New category was created and product was added successfully...");
        }
    }

    public void DeleteProduct() {

        System.out.println("Entetr name of category: ");
        String catname = sc.next();
        System.out.println("Enter Product ID: ");
        double pid = sc.nextDouble();

        for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
            if (Assignment2.ListOfCategories.get(i).getname().equals(catname)) {
                for (int j = 0; j < Assignment2.ListOfCategories.get(i).listOfProducts.size(); j++) {
                    if (Assignment2.ListOfCategories.get(i).listOfProducts.get(j).getid() == pid) {
                        Assignment2.ListOfCategories.get(i).listOfProducts.remove(j);
                        System.out.println("Product removed successfully...");
                    }
                }
                if (Assignment2.ListOfCategories.get(i).listOfProducts.size() == 0) {
                    System.out.println(
                            "Category cannot be empty..Want to add more product(s)? Or else it will be deleted.");
                    System.out.println("Your choice(y/n):");
                    String choice = sc.next();
                    if (choice.equals("y")) {
                        System.out.println("Enter number of products");
                        int n = sc.nextInt();
                        for (int k = 0; k < n; k++) {
                            System.out.println("Product " + String.valueOf(k + 1));
                            System.out.println("Product ID: ");
                            double pid1 = sc.nextDouble();
                            System.out.println("Product name: ");
                            String pname1 = sc.next();
                            Product p = new Product(pid1, pname1);
                            Assignment2.ListOfCategories.get(i).listOfProducts.add(p);
                        }
                    } else {
                        Assignment2.ListOfCategories.remove(i);
                        System.out.println("Category Deleted...");
                    }
                }
            }
        }
    }

    public void SetDiscountOnProduct(Product p) {

        System.out.println("Enter the discount percentage for normal customers: ");
        p.discountfornormalbyadmin = sc.nextDouble();
        System.out.println("Enter the discount percentage for prime customers: ");
        p.discountforprimebyadmin = sc.nextDouble();
        System.out.println("Enter the discount percentage for elite customers: ");
        p.discountforelitebyadmin = sc.nextDouble();
        System.out.println("Discount stored for product successfully...");
    }

    public void AddGiveaway() {

        System.out.println("Enter Deal Id:");
        int gid = sc.nextInt();
        System.out.println("Enter stock of deal you are entering: ");
        int s = sc.nextInt();

        GiveawayDeal g = new GiveawayDeal(gid);
        g.gstock = s;

        System.out.println("Enter product 1's ID: ");
        double pid1 = sc.nextDouble();
        for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
            for (int j = 0; j < Assignment2.ListOfCategories.get(i).listOfProducts.size(); j++) {
                Product p = Assignment2.ListOfCategories.get(i).listOfProducts.get(j);
                if (p.getid() == pid1) {
                    g.GiveawayList.add(p);
                }
            }
        }
        System.out.println("Enter product 2's ID: ");
        double pid2 = sc.nextDouble();
        for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
            for (int j = 0; j < Assignment2.ListOfCategories.get(i).listOfProducts.size(); j++) {
                Product p = Assignment2.ListOfCategories.get(i).listOfProducts.get(j);
                if (p.getid() == pid2) {
                    g.GiveawayList.add(p);
                }
            }
        }

        System.out.println("Enter deal price for normal customers: ");

        g.GiveawayPriceForNormal = sc.nextDouble();
        System.out.println("Enter deal price for prime customers: ");
        g.GiveawayPriceForPrime = sc.nextDouble();
        System.out.println("Enter deal price for elite customers: ");
        g.GiveawayPriceForElite = sc.nextDouble();

        System.out.println("Your 2 products are:");
        for (int i = 0; i < g.GiveawayList.size(); i++) {
            System.out.println(g.GiveawayList.get(i).getname());
        }
        Assignment2.listOfGiveawayDeals.add(g);
        System.out.println("Deal added successfully...");

    }
}

class Customer extends Cred {

    Scanner sc = new Scanner(System.in);

    private String username;
    private String password;
    private int age;
    private String phonenumber;
    private String Status = "Normal";
    private double wallet = 1000.0;

    public ArrayList<Product> Cart = new ArrayList<Product>();
    public ArrayList<Coupon> listofcoupons = new ArrayList<Coupon>();

    Customer(String username, String password) {
        this.username = username;
        this.password = password;
    };

    @Override
    public String getname() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return this.Status;
    }

    public void UpgradeStatus(String newstatus) {
        this.Status = newstatus;
    }

    public double getWallet() {
        return wallet;
    }

    public void AddMoneyToWallet() {
        System.out.println("Enter amount to add to wallet: ");
        double am = sc.nextDouble();
        this.wallet = wallet + am;
        System.out.println("Money added to wallet successfully");
    }

    public void DeductMoneyFromWallet(double amount) {
        this.wallet = wallet - amount;
        System.out.println("Money deducted from wallet successfully");
    }

    public void ExploreProductCatalog() {

        for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
            System.out.println(
                    String.valueOf(i + 1) + ") Category Name: " + Assignment2.ListOfCategories.get(i).getname());
            for (int j = 0; j < Assignment2.ListOfCategories.get(i).listOfProducts.size(); j++) {
                Product p = Assignment2.ListOfCategories.get(i).listOfProducts.get(j);
                System.out.println("    " + String.valueOf(j + 1) + ". Product " + p.getid() + ": " + p.getname()
                        + " | Price = " + p.price + " | Stock = " + String.valueOf(p.stock));

            }
        }
    }

    public void AddProductToCart() { // stock
        System.out.println("Enter your product Id: ");
        double proid = sc.nextDouble();
        int x = 0;
        for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
            for (int j = 0; j < Assignment2.ListOfCategories.get(i).listOfProducts.size(); j++) {
                if (Assignment2.ListOfCategories.get(i).listOfProducts.get(j).getid() == proid) {
                    x++;
                }
            }
        }
        if (x == 0) {
            System.out.println("Invalid input...Try again...");
            AddProductToCart();
        }

        for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
            for (int j = 0; j < Assignment2.ListOfCategories.get(i).listOfProducts.size(); j++) {
                if (Assignment2.ListOfCategories.get(i).listOfProducts.get(j).getid() == proid) {

                    System.out.println("Current stock for this product = "
                            + String.valueOf(Assignment2.ListOfCategories.get(i).listOfProducts.get(j).stock));
                    System.out.println("Enter quantity of this product you want: ");
                    int q = sc.nextInt();
                    if (q > Assignment2.ListOfCategories.get(i).listOfProducts.get(j).stock) {
                        System.out.println("your demand is out of stock...");
                        Assignment2.CustomerFunctionalities(this);
                    }

                    int z = 0;
                    for (int k = 0; k < this.Cart.size(); k++) {
                        if (this.Cart.get(k).getid() == proid) {
                            this.Cart.get(k).quantity += q;
                            this.Cart.get(k).stock -= q;
                            z++;
                        }
                    }

                    if (z == 0) {
                        Assignment2.ListOfCategories.get(i).listOfProducts.get(j).quantity = q;
                        Assignment2.ListOfCategories.get(i).listOfProducts
                                .get(j).stock = Assignment2.ListOfCategories.get(i).listOfProducts.get(j).stock - q;
                        this.Cart.add(Assignment2.ListOfCategories.get(i).listOfProducts.get(j));
                    }

                }
            }
        }
        ViewCart();
    }

    public void ExploreGiveawayDeal(GiveawayDeal g) {
        System.out.println("Deal Id: " + g.getid());
        System.out.println("Products in this deal: ");
        for (int i = 0; i < g.GiveawayList.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + g.GiveawayList.get(i).getname());
        }
        if (this.getStatus() == "Normal") {
            System.out.println("Total Price: " + g.GiveawayPriceForNormal);
        }
        if (this.getStatus() == "Elite") {
            System.out.println("Total Price: " + g.GiveawayPriceForElite);
        }
        if (this.getStatus() == "Prime") {
            System.out.println("Total Price: " + g.GiveawayPriceForPrime);
        }
    }

    public void ShowAvailableDeal(GiveawayDeal g) {
        System.out.println("Deal Id: " + g.getid() + " | Stock = " + g.gstock);
        System.out.println("Products in this deal: ");
        for (int i = 0; i < g.GiveawayList.size(); i++) {
            System.out.println("    " + String.valueOf(i + 1) + ". " + g.GiveawayList.get(i).getname());
        }

        System.out.println("Total Price for normal customers: " + g.GiveawayPriceForNormal);
        System.out.println("Total Price for prime customers: " + g.GiveawayPriceForPrime);
        System.out.println("Total Price for elite customers: " + g.GiveawayPriceForElite);
    }

    public void AddDealtoCart() {
        System.out.println("These are the deals:");
        for (int i = 0; i < Assignment2.listOfGiveawayDeals.size(); i++) {
            GiveawayDeal g = Assignment2.listOfGiveawayDeals.get(i);
            System.out.println("Deal " + g.getid() + ": ");
            System.out.println("Current stock: " + g.gstock);
            for (int j = 0; j < g.GiveawayList.size(); j++) {
                System.out.println(String.valueOf(j + 1) + ". " + g.GiveawayList.get(j).getname());
            }
            if (getStatus() == "Normal") {
                System.out.println("Price: " + g.GiveawayPriceForNormal);
            }
            if (getStatus() == "Elite") {
                System.out.println("Price: " + g.GiveawayPriceForElite);
            }
            if (getStatus() == "Prime") {
                System.out.println("Price: " + g.GiveawayPriceForPrime);
            }
        }

        System.out.println("Enter deal id to select: ");
        int uff = sc.nextInt();

        int w = 0;
        for (int i = 0; i < Assignment2.listOfGiveawayDeals.size(); i++) {
            if (Assignment2.listOfGiveawayDeals.get(i).getid() == uff) {
                w++;
            }
        }

        if (w == 0) {
            System.out.println("Invalid input...Try again...");
            AddDealtoCart();
        }

        System.out.println("Quantity of this deal you want: ");
        int q = sc.nextInt();

        for (int i = 0; i < Assignment2.listOfGiveawayDeals.size(); i++) {
            GiveawayDeal g = Assignment2.listOfGiveawayDeals.get(i);
            if (g.getid() == uff) {
                Product p = new Product((double) uff, "Deal " + String.valueOf(uff));
                if (q > g.gstock) {
                    System.out.println("your demand is out of stock...");
                    Assignment2.CustomerFunctionalities(this);
                }
                p.quantity = q;
                p.stock = g.gstock - q;
                p.pricefornormalindeal = g.GiveawayPriceForNormal;
                p.priceforeliteindeal = g.GiveawayPriceForElite;
                p.priceforprimeindeal = g.GiveawayPriceForPrime;
                Cart.add(p);

                System.out.println("Deal added to cart successfully...");
            }
        }
        ViewCart();
    }

    public void viewCoupons() {
        if (this.listofcoupons.size() == 0) {
            double tp = 0;
            for (int i = 0; i < this.Cart.size(); i++) {
                if (Status.equals("Normal")) {
                    System.out.println("No coupons for normal customers...");
                }
                if (Status.equals("Prime")) {
                    if (this.Cart.get(i).getname().split(" ")[0].equals("Deal")) {
                        tp = tp + this.Cart.get(i).priceforprimeindeal;
                    } else {
                        tp = tp + this.Cart.get(i).finalpriceforprimebeforecoupon;
                    }
                }
                if (Status.equals("Elite")) {
                    if (this.Cart.get(i).getname().split(" ")[0].equals("Deal")) {
                        tp = tp + this.Cart.get(i).priceforeliteindeal;
                    } else {
                        tp = tp + this.Cart.get(i).finalpriceforelitebeforecoupon;
                    }
                }
            }
            if (tp >= 5000) {
                if (Status.equals("Prime")) {
                    Random rand = new Random();
                    int n = rand.nextInt(1, 3);
                    System.out.println("Congratulations, you have received " + n + " coupons...");
                    for (int i = 1; i < n + 1; i++) {
                        int d = rand.nextInt(5, 16);
                        Coupon co = new Coupon(i, d);
                        System.out.println(
                                "Coupon " + String.valueOf(i) + " with discount percentage: " + String.valueOf(d));
                        this.listofcoupons.add(co);
                    }
                }
                if (Status.equals("Elite")) {
                    Random rand = new Random();
                    int n = rand.nextInt(3, 5);
                    System.out.println("Congratulations, you have received " + n + " coupons...");
                    for (int i = 1; i < n + 1; i++) {
                        int d = rand.nextInt(5, 16);
                        Coupon co = new Coupon(i, d);
                        System.out.println(
                                "Coupon " + String.valueOf(i) + " with discount percentage: " + String.valueOf(d));
                        this.listofcoupons.add(co);
                    }

                }
            }

            else {
                System.out.println("No coupons...");
            }
        } else {
            System.out.println("Your coupons: ");
            for (int i = 0; i < this.listofcoupons.size(); i++) {
                System.out.println("Coupon " + String.valueOf(i + 1) + " with discount percentage: "
                        + this.listofcoupons.get(i).discountofferedbycoupon);
            }
        }
    }

    public int GetMaxCouponDiscount() {
        int max = 0;
        for (int i = 0; i < this.listofcoupons.size(); i++) {
            if (this.listofcoupons.get(i).discountofferedbycoupon > max) {
                max = this.listofcoupons.get(i).discountofferedbycoupon;
            }
        }
        return max;
    }

    public void ViewCart() {
        if (this.Cart.size() == 0) {
            System.out.println("Your cart is empty");
        } else {
            System.out.println("Your cart: ");

            for (int i = 0; i < this.Cart.size(); i++) {
                System.out.println(
                        "     Product: " + this.Cart.get(i).getname() + " ; Quantity: " + this.Cart.get(i).quantity);
            }
        }
    }

    public double min(double a, double b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public int z = 0;

    public void CheckoutCart() {
        if (this.Cart.size() == 0) {
            System.out.println("Your cart is empty");
        }

        double totalprice = 0;

        double pp = 0;

        System.out.println("Your cart: ");
        for (int i = 0; i < this.Cart.size(); i++) {

            if (this.Status.equals("Normal")) {
                if (this.Cart.get(i).getname().split(" ")[0].equals("Deal")) {
                    pp = this.Cart.get(i).pricefornormalindeal * this.Cart.get(i).quantity;
                } else {
                    double c = this.Cart.get(i).price;
                    double a = c; // price because of normal status
                    double b = this.Cart.get(i).price * (100 - this.Cart.get(i).discountfornormalbyadmin) / 100; // price
                                                                                                                 // //
                                                                                                                 // adm
                                                                                                                 // //
                                                                                                                 // discount
                    pp = min(a, b);
                    pp = pp * this.Cart.get(i).quantity;
                    this.Cart.get(i).finalpricefornormalbeforecoupon = pp;
                }
            }
            if (this.Status.equals("Prime")) {
                if (this.Cart.get(i).getname().split(" ")[0].equals("Deal")) {
                    pp = this.Cart.get(i).priceforprimeindeal * this.Cart.get(i).quantity;
                } else {
                    double c = this.Cart.get(i).price;
                    double a = 0.95 * c; // price because of prime status
                    double b = this.Cart.get(i).price * (100 - this.Cart.get(i).discountforprimebyadmin) / 100; // price
                                                                                                                // //
                                                                                                                // discount
                    double e = this.Cart.get(i).priceaftercoupon;
                    pp = min(a, b);
                    pp = min(pp, e);
                    pp = pp * this.Cart.get(i).quantity;
                    this.Cart.get(i).finalpriceforprimebeforecoupon = pp;
                }
            }
            if (this.Status.equals("Elite")) {
                if (this.Cart.get(i).getname().split(" ")[0].equals("Deal")) {
                    pp = this.Cart.get(i).priceforeliteindeal * this.Cart.get(i).quantity;
                } else {
                    double c = this.Cart.get(i).price;
                    double a = 0.9 * c; // price because of elite status
                    double b = this.Cart.get(i).price * (100 - this.Cart.get(i).discountforelitebyadmin) / 100; // price
                                                                                                                // //
                                                                                                                // discount
                    double e = this.Cart.get(i).priceaftercoupon;
                    pp = min(a, b);
                    pp = min(pp, e);
                    pp = pp * this.Cart.get(i).quantity;
                    this.Cart.get(i).finalpriceforelitebeforecoupon = pp;
                }
            }
            System.out.println("Product name: " + this.Cart.get(i).getname() + " ; Quantity: "
                    + this.Cart.get(i).quantity + " ; Price: " + String.valueOf(pp));
            totalprice = totalprice + pp;
        }
        System.out.println("Total price of items: " + String.valueOf(totalprice));

        if (z == 0) {
            if (Status.equals("Normal")) {
                z = 0;
            } else {
                z = 1;
            }
            viewCoupons();

            if (this.listofcoupons.size() != 0) {
                double coupondiscount = (double) GetMaxCouponDiscount();
                System.out.println("Your max discount from coupon is: " + String.valueOf(coupondiscount));
                System.out.println("Enter product ID on which you want to avail this coupon:");
                double proid = sc.nextDouble();
                for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
                    for (int j = 0; j < Assignment2.ListOfCategories.get(i).listOfProducts.size(); j++) {
                        if (Assignment2.ListOfCategories.get(i).listOfProducts.get(j).getid() == (proid)) {
                            if (Status.equals("Prime")) {
                                Assignment2.ListOfCategories.get(i).listOfProducts
                                        .get(j).priceaftercoupon = Assignment2.ListOfCategories.get(i).listOfProducts
                                                .get(j).price * (100 - coupondiscount) / 100;
                            } else if (Status.equals("Elite")) {
                                Assignment2.ListOfCategories.get(i).listOfProducts
                                        .get(j).priceaftercoupon = Assignment2.ListOfCategories.get(i).listOfProducts
                                                .get(j).price * (100 - coupondiscount) / 100;
                            }
                        }
                    }

                }
                CheckoutCart();

            }
        }
        if (Status.equals("Normal")) {
            System.out.println("Delivery charges: " + String.valueOf(100 + 0.05 * totalprice));
            totalprice = totalprice + 100 + 0.05 * totalprice;
            System.out.println("Amount to be paid: " + totalprice);
            System.out.println("Delivery will be made within 7-10 days");

        }
        if (Status.equals("Prime")) {
            System.out.println("Delivery charges: " + String.valueOf(100 + 0.02 * totalprice));
            totalprice = totalprice + 100 + 0.02 * totalprice;
            System.out.println("Amount to be paid: " + totalprice);
            System.out.println("Delivery will be made within 3-6 days");

        }
        if (Status.equals("Elite")) {
            Random rand = new Random();

            int n = rand.nextInt(0, Assignment2.ListOfCategories.size());
            int m = rand.nextInt(0, Assignment2.ListOfCategories.get(n).listOfProducts.size());
            Product p = Assignment2.ListOfCategories.get(n).listOfProducts.get(m);
            System.out.println("Your free produt from our side: " + p.getname());

            System.out.println("Delivery charges: 100");
            totalprice = totalprice + 100;
            System.out.println("Amount to be paid: " + totalprice);
            System.out.println("Delivery will be made within 2 days");

        }
        System.out.println("Do you want to checkout? (y/n)");
        String choice = sc.next();
        if (choice.equals("y")) {
            if (wallet >= totalprice) {
                wallet = wallet - totalprice;
            } else {
                System.out.println("Insufficient amount...your current account balance is " + getWallet());
                AddMoneyToWallet();
                wallet = wallet - totalprice;
            }
            System.out.println("Your order has been placed !!!");
            System.out.println("Money deducted from wallet...");
            this.Cart.clear();
            this.listofcoupons.clear();
            Assignment2.CustomerFunctionalities(this);
        } else if (choice.equals("n")) {
            Assignment2.CustomerFunctionalities(this);
        }

    }
}

class Category extends Cred {

    private int id;
    private String categoryname;

    @Override
    public int getid() {
        return id;
    }

    @Override
    public String getname() {
        return categoryname;
    }

    public ArrayList<Product> listOfProducts = new ArrayList<Product>();

    Category(int id, String categoryname) {
        this.id = id;
        this.categoryname = categoryname;
    }
}

class Product {

    private double pid;
    private String pname;
    public int stock;
    public int quantity;
    public String details;

    public double getid() {
        return pid;
    }

    public String getname() {
        return pname;
    }

    // discount for each product that admin sets
    double discountfornormalbyadmin = 0.0;
    double discountforelitebyadmin = 0.0;
    double discountforprimebyadmin = 0.0;

    double price;

    // giveawaydeal prices - handled while adding the deal;
    double pricefornormalindeal;
    double priceforeliteindeal;
    double priceforprimeindeal;

    double finalpricefornormalbeforecoupon;
    double finalpriceforprimebeforecoupon;
    double finalpriceforelitebeforecoupon;

    double priceaftercoupon = Double.POSITIVE_INFINITY;

    Product(double pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }
}

class GiveawayDeal extends Cred {
    private int gid;

    int gstock;
    int gquantity;

    @Override
    public int getid() {
        return gid;
    }

    public ArrayList<Product> GiveawayList = new ArrayList<Product>();
    public double GiveawayPriceForNormal;
    public double GiveawayPriceForElite;
    public double GiveawayPriceForPrime;

    GiveawayDeal(int gid) {
        this.gid = gid;
    }
}

class Coupon {
    int id;
    int discountofferedbycoupon;

    Coupon(int id, int d) {
        this.id = id;
        this.discountofferedbycoupon = d;
    }
}

public class Assignment2 {

    static Scanner sc = new Scanner(System.in);

    public static ArrayList<Customer> listOfCustomers = new ArrayList<Customer>();
    public static ArrayList<Category> ListOfCategories = new ArrayList<Category>();
    public static ArrayList<GiveawayDeal> listOfGiveawayDeals = new ArrayList<GiveawayDeal>();

    public static void EnterAsCustomer() {
        System.out.println("1) Sign Up");
        System.out.println("2) Login");
        System.out.println("3) Back");
        int z = sc.nextInt();

        switch (z) {

            case 1:

                System.out.println("Enter Username:");
                String u = sc.next();
                System.out.println("Enter Password:");
                String p = sc.next();
                System.out.println("Enter Age: ");
                int a = sc.nextInt();
                System.out.println("Enter Phone Number: ");
                String pn = sc.next();

                Customer nc = new Customer(u, p);

                listOfCustomers.add(nc);

                System.out.println("Customer Succesfully registered!!");

                EnterAsCustomer();

            case 2:

                System.out.println("Enter Username:");
                String u1 = sc.next();
                System.out.println("Enter Password:");
                String p1 = sc.next();

                int l = 1;

                for (int i = 0; i < listOfCustomers.size(); i++) {
                    Customer o = listOfCustomers.get(i);
                    if ((o.getname().equals(u1)) && (o.getPassword().equals(p1))) {
                        CustomerFunctionalities(o);
                    }
                }
                l = 0;
                if (l == 0) {
                    System.out.println("Invalid credentials, please try again.");
                    System.out.println("Or sign up if you have not till now.");
                }

            case 3:
                FLIPZON();
        }
    }

    public static void CustomerFunctionalities(Customer c) {
        System.out.println("Welcome!!");
        System.out.println("1) Browse products");
        System.out.println("2) Browse deals");
        System.out.println("3) Add a product to cart");
        System.out.println("4) Add products in deal to cart");
        System.out.println("5) View coupons");
        System.out.println("6) Check account balance");
        System.out.println("7) View cart");
        System.out.println("8) Empty cart");
        System.out.println("9) Checkout cart");
        System.out.println("10) Upgrade customer status");
        System.out.println("11) Add amount to wallet");
        System.out.println("12) Back");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                c.ExploreProductCatalog();
                CustomerFunctionalities(c);
            case 2:
                System.out.println("The deals are: ");
                for (int i = 0; i < listOfGiveawayDeals.size(); i++) {

                    c.ExploreGiveawayDeal(listOfGiveawayDeals.get(i));
                }
                CustomerFunctionalities(c);
            case 3:
                c.AddProductToCart();
                CustomerFunctionalities(c);
            case 4:
                c.AddDealtoCart();
                CustomerFunctionalities(c);
            case 5:
                c.viewCoupons();
                CustomerFunctionalities(c);
            case 6:
                System.out.println("Your account balance: " + String.valueOf(c.getWallet()));
                CustomerFunctionalities(c);
            case 7:
                c.ViewCart();
                CustomerFunctionalities(c);
            case 8:
                c.Cart.clear();
                System.out.println("YourCart is now empty");
                c.listofcoupons.clear();
                CustomerFunctionalities(c);
            case 9:
                c.CheckoutCart();
                CustomerFunctionalities(c);
            case 10:
                System.out.println("You want to upgrade to which status:");
                System.out.println("1. Prime - 200 Rs.");
                System.out.println("2. Elite - 300 Rs.");
                System.out.println("3. None");
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        c.UpgradeStatus("Prime");
                        c.DeductMoneyFromWallet(200.0);
                        System.out.println("You are now upgraded to Prime customer!");
                        CustomerFunctionalities(c);
                    case 2:
                        c.UpgradeStatus("Elite");
                        c.DeductMoneyFromWallet(300.0);
                        System.out.println("You are now upgraded to Elite customer!");
                        CustomerFunctionalities(c);
                    case 3:
                        CustomerFunctionalities(c);
                }
            case 11:
                c.AddMoneyToWallet();
                CustomerFunctionalities(c);
            case 12:
                EnterAsCustomer();
        }
    }

    public static void AdminFunctionalities(Admin a) {
        System.out.println("Welcome " + a.getname());
        System.out.println("Please choose any one of the following actions:");
        System.out.println("    1) Add category");
        System.out.println("    2) Delete category");
        System.out.println("    3) Add Product");
        System.out.println("    4) Delete Product");
        System.out.println("    5) Set Discount on Product");
        System.out.println("    6) Add giveaway deal");
        System.out.println("    7) Back");

        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                a.AddCategory();
                Assignment2.AdminFunctionalities(a);
            case 2:
                a.DeleteCategory();
                Assignment2.AdminFunctionalities(a);
            case 3:
                a.AddProduct();
                Assignment2.AdminFunctionalities(a);
            case 4:
                a.DeleteProduct();
                Assignment2.AdminFunctionalities(a);
            case 5:
                System.out.println("Enter the product ID to set the discount: ");
                double proid = sc.nextDouble();
                for (int i = 0; i < Assignment2.ListOfCategories.size(); i++) {
                    for (int j = 0; j < Assignment2.ListOfCategories.get(i).listOfProducts.size(); j++) {
                        if (Assignment2.ListOfCategories.get(i).listOfProducts.get(j).getid() == proid) {
                            Product p = Assignment2.ListOfCategories.get(i).listOfProducts.get(j);
                            a.SetDiscountOnProduct(p);
                        }
                    }
                }
                Assignment2.AdminFunctionalities(a);
            case 6:
                a.AddGiveaway();
                Assignment2.AdminFunctionalities(a);
            case 7:
                FLIPZON();
        }
    }

    public static void FLIPZON() {
        System.out.println("");
        System.out.println("----------------------WELCOME TO FLIPZOIN---------------------");
        System.out.println("");
        System.out.println("    1) Enter as Admin");
        System.out.println("    2) Explore the Product Catalog");
        System.out.println("    3) Show Available Deals");
        System.out.println("    4) Enter as Customer");
        System.out.println("    5) Exit the Application");

        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                Admin a = new Admin(null, null);
                a.EnterAsAdmin();
            case 2:
                Customer c = new Customer("null", "null");
                c.ExploreProductCatalog();
                FLIPZON();
            case 3:
                Customer c1 = new Customer("null", "null");
                if (listOfGiveawayDeals.size() == 0) {
                    System.out.println("No Giveaway Deals till now");
                }
                for (int i = 0; i < listOfGiveawayDeals.size(); i++) {
                    c1.ShowAvailableDeal(listOfGiveawayDeals.get(i));
                }
                FLIPZON();
            case 4:
                EnterAsCustomer();
            case 5:
                System.out.println("Are you sure you want to exit? (y/n)");
                String h = sc.next();
                if (h.equals("y")) {
                    System.out.println("Exiting from FLIPZON...");
                    System.exit(0);
                } else {
                    FLIPZON();
                }
        }
    }

    public static void main(String[] args) {
        FLIPZON();
    }
}
