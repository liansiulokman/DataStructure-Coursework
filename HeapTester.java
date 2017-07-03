package comp2011.lecA;

import java.util.Arrays;
import java.util.stream.Collectors;

class Student {
    int id;
    String name;
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String toString() {return name;}
}

public class HeapTester {

//    public void heapSort(Student[] a) {
//        Heap<Student> heap = new Heap<Student>(a.length);
//        for (Student stu:a)
//            heap.insert(stu.id, stu);
//        for (int i = a.length - 1; i >= 0; i--)
//            a[i] = heap.remove().data;
//    }
//    
	
	
    public static void main(String[] args) {
        Heap<Student> heap = new Heap<Student>(20);
        heap.insert(139, new Student(14074139,"CHAN Tsz Sang"));
        heap.insert(208, new Student(14074208,"MA Cheung Yin"));
        heap.insert( 47, new Student(14074047,"LOK Iok Seng"));
        heap.insert(116, new Student(14074116,"WU Wing Sze"));
        heap.insert( 77, new Student(14074077,"CHAN Sing Kam"));
        heap.insert(307, new Student(14074307,"HO Chong Yin"));
        heap.insert(183, new Student(14074183,"LAU Ki Yuk"));
        heap.insert(641, new Student(14074641,"MAN Wing Fung"));
        heap.insert(199, new Student(14074199,"TAM Wing Yim"));
        heap.insert(123, new Student(14074123,"YAU Kam Po"));
        heap.insert(  1, new Student(14074001,"LI Tsz Fung"));
        
        //System.out.print(heap);
        /*
        heap.deleteMax();
        System.out.println("=====================");
        System.out.print(heap);

        if (true) return;
        
        int[] ids = {14074176, 14073064, 14074077, 14072624, 14074139, 14078364, 14073927, 14111483, 13104501, 13103214, 14074374, 13067841, 14073743, 14067925, 14074084, 14073483, 14073575, 14108085, 14070156, 14073179, 14112466, 13103633, 14122359, 14110914, 13103831, 14074307, 14073255, 13102033, 14072196, 14075371, 12091954, 13066432, 15019373, 14072868, 14073568, 14073957, 14072997, 13088815, 14073423, 14074436, 14074183, 14073858, 14072882, 14074406, 14059807, 14074367, 14073706, 14073697, 14072822, 14074802, 13104448, 14074001, 13103495, 14073156, 13107851, 12131674, 12132473, 14074413, 14111119, 14074763, 14075898, 14074047, 15019405, 14074208, 14110562, 14111439, 14074641, 15044551, 14071116, 13066418, 14122281, 14073729, 14111773, 14075196, 13104616, 15019389, 13104584, 14075173, 14073773, 14040502, 13000125, 14074199, 14116974, 14074298, 14075081, 13105383, 14073409, 15015217, 14073789, 14040753, 14073446, 14074146, 13067217, 14074337, 14069539, 13102581, 14074855, 14108474, 14074116, 12131927, 14109662, 14074123, 14072898, 14073439, 14073895, 14074169, 14110295, 14073582, 14074153, 13104188, 13104036, 14109328, 13104363, 12135832};        
        String[] names = {"CHAN Chun Yin Anthony", "CHAN Kwok Leung", "CHAN Sing Kam", "CHAN Tsz Lun", "CHAN Tsz Sang", "CHAN Wai Ying", "CHAN Wing Shing", "CHEN Haiyu", "CHEN Yujing", "CHEN Yunkun", "CHENG Chi Hang", "CHEUNG Ching Hung", "CHEUNG Chun Pan", "CHEUNG Ming Yin", "CHEUNG Suet Ching", "CHEUNG Wai Lun", "CHEUNG Yiu Chi", "CHIANG Pin Huey", "CHING Ka Ho", "FAN Yun Fung", "FU Kuo-hao", "FU Xuandi", "FUNG Fang Ching", "GU Jinshan", "HAN Yunpeng", "HO Chong Yin", "HO Siu Tung", "HO Ying", "HON Wing Cheong", "IP Ka Yiu", "IP Kin Chung", "KAN Cheuk Ho", "KHAN Usman Ali", "KO Wing Fei Ardea", "KWEE Cheuk Yan", "KWOK Cheuk Kwan", "KWOK Sai Hoi", "LAI Kai Hang", "LAM Kin Tsun", "LAM Ping Him", "LAU Ki Yuk", "LAU Wai Yin", "LEE Ho Yin", "LEE Kiu Lun", "LEE Siu Kit", "LEUNG Chun Yin", "LEUNG Ka Chun", "LEUNG Lam Hung", "LEUNG Shek Hin", "LEUNG Tin Ho", "LI Su", "LI Tsz Fung", "LI Yunhao", "LING Kam Fung", "LIU Chang", "LIU Lin", "LIU Sining", "LIU Yin Ching", "LIU Yitong", "LO Yu Yan", "LOK Chun Ting", "LOK Iok Seng", "LY Joseph", "MA Cheung Yin", "MA Mingyu", "MA Zhenyuan", "MAN Wing Fung", "MOK Tsz Pui", "NG Sze Man", "NG Tsang", "NG Tsz Wa", "NG Winsome", "OUYANG Xiating", "PANG Chun Yin", "PENG Tao", "SAVERY-NOSWORTHY Craig Reece Amari", "SHI Yuanjing", "SHU Mung Sha", "SHUM Ka Lok", "SIU Lok Man", "SIU Tung Sing", "TAM Wing Yim", "TANAKA Masamichi", "TONG Lok Yi", "TONG Wai Hung", "TSOI Kai Wai", "TSUI Chung Tat", "WEN Shuqiang", "WONG Chak Hang Franco", "WONG Chi Hung", "WONG Kam Pang", "WONG Ki Wang", "WONG Siu Man Lawrence", "WONG Sze Ho Stanley", "WONG Tsz Yan", "WU Chao", "WU Mei Fung", "WU Vincent Wei Can", "WU Wing Sze", "YANG Lu", "YAO Yuchen", "YAU Kam Po", "YEUNG Tsz Fung", "YIP Kam Yun", "YIU Kin Lui", "YIU Wai Ki", "YU Maichao", "YUEN Chung Lun", "YUNG Hoi Ki", "ZHANG Yi", "ZHENG Hongyi", "ZHOU Yi", "ZHU Yidi", "ZHUO Yue"};
        Student[] students = new Student[ids.length];
        for (int i = 0; i < ids.length; i++)
            students[i] = new Student(ids[i], names[i]);
        heap = new Heap<Student>(120);
        */
        
        
        int[] ids = {14074139, 14074208, 14074047, 14074116, 14074077, 14074307, 14074183, 14074641, 14074199, 14074123, 14074001};
        String[] names = {"CHAN Tsz Sang", "MA Cheung Yin", "LOK Iok Seng", "WU Wing Sze", "CHAN Sing Kam", "HO Chong Yin", "LAU Ki Yuk", "MAN Wing Fung", "TAM Wing Yim", "YAU Kam Po", "LI Tsz Fung"};
        Student[] students = new Student[ids.length];
        
        for (int i = 0; i < ids.length; i++){
            students[i] = new Student(ids[i], names[i]);
            System.out.println(ids[i]+": "+students[i]);
        }
        System.out.println("\n==========After In-place Heap Sort (Max Heap)===========\n");
        heap.inplaceHeapSort(ids, students);
        for(int y=0;y<ids.length;y++)
        	System.out.println(ids[y]+": "+students[y]);
    }
}
