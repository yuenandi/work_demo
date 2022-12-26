package org.example.utils;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class MakeData {

    public static void main(String[] args) {
        // ����һ�����������������������������ɶ��������������ݣ�������д�� ��Ϊ����Ҫ10���������
        int dataLength = 100;
        List<NameData> dataList = makeData(dataLength);
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println("������" + dataList.get(i).getName() + ",���֤�ţ�" + dataList.get(i).getIdCardNumber());
        }
    }

    /**
     * �����������+���֤��
     *
     * @param dataLength
     * @return
     */
    public static List<NameData> makeData(int dataLength) {
        // дһ���ټ��յ�����
        String[] Surname = {"��", "Ǯ", "��", "��", "��", "��", "֣", "��", "��", "��", "��", "��", "��", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "ʩ", "��", "��", "��", "��", "��", "��", "κ", "��",
                "��", "��", "л", "��", "��", "��", "ˮ", "�", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "³", "Τ", "��", "��", "��", "��", "��", "��", "��", "��", "Ԭ", "��", "ۺ", "��", "ʷ", "��", "��",
                "��", "�", "Ѧ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "ʱ", "��", "Ƥ", "��", "��", "��", "��", "��", "Ԫ", "��", "��", "��", "ƽ", "��", "��", "��", "��",
                "��", "Ҧ", "��", "տ", "��", "��", "ë", "��", "��", "��", "��", "��", "�", "��", "��", "��", "��",
                "̸", "��", "é", "��", "��", "��", "��", "��", "��", "ף", "��", "��", "��", "��", "��", "��", "ϯ",
                "��", "��", "ǿ", "��", "·", "¦", "Σ", "��", "ͯ", "��", "��", "÷", "ʢ", "��", "��", "��", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "֧", "��", "��", "��", "¬",
                "Ī", "��", "��", "��", "��", "��", "��", "Ӧ", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "��", "��", "��", "ʯ", "��", "��", "ť", "��", "��", "��", "��", "��", "��", "½", "��", "��", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "ɽ", "��", "��", "��", "�", "��", "ȫ", "ۭ", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "��", "ղ", "��", "��", "Ҷ", "��", "˾", "��", "۬", "��", "��", "��", "ӡ", "��", "��", "��", "��",
                "ۢ", "��", "��", "��", "��", "��", "��", "׿", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "��", "˫", "��", "ݷ", "��", "��", "̷", "��", "��", "��", "��", "��", "��", "��", "Ƚ", "��", "۪",
                "Ӻ", "ȴ", "�", "ɣ", "��", "�", "ţ", "��", "ͨ", "��", "��", "��", "��", "��", "��", "ũ", "��",
                "��", "ׯ", "��", "��", "��", "��", "��", "Ľ", "��", "��", "ϰ", "��", "��", "��", "��", "��", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "��", "��", "»", "��", "��", "ŷ", "�", "��", "��", "ε", "Խ", "��", "¡", "ʦ", "��", "��", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "ɳ", "ؿ", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "ۣ", "��", "Ȩ", "��", "��",
                "��", "��", "��", "��", "��", "��", "˧", "��", "��", "��", "�C", "��", "��", "��", "��", "��", "��",
                "��", "��", "��", "۳", "Ϳ", "��", "��", "Ĳ", "��", "٦", "��", "��", "ī", "��", "��", "��", "��",
                "��", "��", "١", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "չ", "��", "̴", "��",
                "��", "��", "��", "˴", "¥", "��", "ð", "��", "ֿ", "��", "��", "��", "��", "ԭ", "��", "��", "��",
                "��", "��", "�", "��", "��", "��", "�", "��", "��", "��", "��", "��", "��", "��", "ľ", "��", "��",
                "ۨ", "��", "ö", "��", "��", "�", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
                "��", "��", "��", "��", "��", "��", "��", "��", "��", "�G", "��ٹ", "˾��", "�Ϲ�", "ŷ��", "�ĺ�",
                "���", "����", "����", "����", "�ʸ�", "����", "ξ��", "����", "�̨", "��ұ", "����", "���", "����", "����",
                "̫��", "����", "����", "����", "��ԯ", "���", "����", "����", "����", "Ľ��", "����", "����", "˾ͽ", "˾��",
                "أ��", "˾��", "����", "����", "�ӳ�", "���", "��ľ", "����", "����", "���", "����", "����", "����", "�ذ�",
                "�й�", "�׸�", "����", "�θ�", "����", "����", "΢��", "����", "����", "����", "����", "�Ϲ�", "����", "����",
                "����", "̫ʷ", "�ٳ�", "����", "��ͻ", "����", "����", "����", "��ĸ", "˾��", "����", "Ӻ��", "����", "����",
                "����", "��®", "����", "�Ϲ�", "����", "����"};
        Random random = new Random(System.currentTimeMillis());

        // List���ϴ洢����+���֤��ʵ����
        List<NameData> nameDataList = new ArrayList<>();
        // ѭ��dataLength ��Ҫ������ô��������������
        for (int i = 0; i < dataLength; i++) {
            // ���ȡ��һ������
            int index = random.nextInt(Surname.length - 1);
            String nameSur = Surname[index];
            String name = nameSur;
            NameData nameData = new NameData();
            // nextBoolean() �������ڴӸ�����������������еõ���һ��α���ȷֲ��Ĳ���ֵ��
            // �����������ֵ��ȷ��������һ���ֻ���������
            if (random.nextBoolean()) {
                name += getName() + getName();
            } else {
                name += getName();
            }
            nameData.setName(name);
            // �ٸ�ÿ�����ɵ����ּӸ�������֤��
            String idCardNumber = makeidCardNumber();
            nameData.setIdCardNumber(idCardNumber);
            nameDataList.add(nameData);
            //System.out.println(name + ":" + idCardNumber);
        }
        return nameDataList;
    }

    /**
     * �����ȡһ���������������
     *
     * @return
     */
    public static String getName() {
        String nameStr = "";
        int highCode, lowCode;
        Random random = new Random();
        // ���룬0xA0��ͷ���ӵ�16����ʼ����0xB0=11*16=176,16~55һ�����֣�56~87��������
        highCode = (176 + Math.abs(random.nextInt(71)));
        random = new Random();
        // λ�룬0xA0��ͷ����Χ��1~94��
        lowCode = 161 + Math.abs(random.nextInt(94));

        byte[] codeArr = new byte[2];
        codeArr[0] = (new Integer(highCode)).byteValue();
        codeArr[1] = (new Integer(lowCode)).byteValue();
        try {
            // ��λ����ϳɺ���
            nameStr = new String(codeArr, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return nameStr;
    }

    /**
     * ����������֤��
     *
     * @return
     */
    private static String makeidCardNumber() {
        // ���֤����ɶ���������ڵأ���1����6λ�� + �������ڣ���7����14λ�� + �仧�ɳ������루��15��16λ�� + �Ա���루��17λ�� + ��֤�루��18λ��
        // �������ڵ�(�Ա���Ϊ��)
        Map<String, Integer> registerLocation = new HashMap<>();
        registerLocation.put("������", 110000);
        registerLocation.put("��Ͻ��", 110100);
        registerLocation.put("������", 110101);
        registerLocation.put("������", 110102);
        registerLocation.put("������", 110103);
        registerLocation.put("������", 110104);
        registerLocation.put("������", 110105);
        registerLocation.put("��̨��", 110106);
        registerLocation.put("ʯ��ɽ��", 110107);
        registerLocation.put("������", 110108);
        registerLocation.put("��ͷ����", 110109);
        registerLocation.put("��ɽ��", 110111);
        registerLocation.put("ͨ����", 110112);
        registerLocation.put("˳����", 110113);
        registerLocation.put("��ƽ��", 110114);
        registerLocation.put("������", 110115);
        registerLocation.put("������", 110116);
        registerLocation.put("ƽ����", 110117);
        registerLocation.put("��", 110200);
        registerLocation.put("������", 110228);
        registerLocation.put("������", 110229);
        registerLocation.put("�����", 120000);
        registerLocation.put("��Ͻ��", 120100);
        registerLocation.put("��ƽ��", 120101);
        registerLocation.put("�Ӷ���", 120102);
        registerLocation.put("������", 120103);
        registerLocation.put("�Ͽ���", 120104);
        registerLocation.put("�ӱ���", 120105);
        registerLocation.put("������", 120106);
        registerLocation.put("������", 120110);
        registerLocation.put("������", 120111);
        registerLocation.put("������", 120112);
        registerLocation.put("������", 120113);
        registerLocation.put("������", 120114);
        registerLocation.put("������", 120115);
        registerLocation.put("��", 120200);
        registerLocation.put("������", 120221);
        registerLocation.put("������", 120223);
        registerLocation.put("������", 120225);

        StringBuffer strBuffer = new StringBuffer();
        String rlc = randomLocationCode(registerLocation);
        // ����
        strBuffer.append(rlc);

        // ���֤��
        String rbd = randomBirthday();
        strBuffer.append(rbd);
        // 15��16��17λ
        String rc = randomCode();
        strBuffer.append(rc);
        // ����ǰʮ��λ��ȡ��ʮ��λ
        String eighteenth = verificationCode(strBuffer.toString());
        strBuffer.append(eighteenth);

        return strBuffer.toString();
    }

    /**
     * �����ȡ����
     *
     * @param registerLocation
     * @return
     */
    public static String randomLocationCode(Map<String, Integer> registerLocation) {
        int index = (int) (Math.random() * registerLocation.size());
        Collection<Integer> values = registerLocation.values();
        Iterator<Integer> it = values.iterator();
        int i = 0;
        int locationCode = 0;
        while (i <= index && it.hasNext()) {
            if (i == index) {
                locationCode = it.next();
            }
            i++;
        }

        return String.valueOf(locationCode);
    }

    /**
     * ������ɳ�������
     *
     * @return
     */
    public static String randomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 60) + 1950);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }

    /**
     * �����ȡ�仧�ɳ������루��15��16λ�� + �Ա���루��17λ��
     * ֱ��������λ��
     *
     * @return
     */
    public static String randomCode() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }

    /**
     * ���ɵ�18λ���֤��
     *
     * @param
     * @return ���֤У����ļ��㷽��
     * ��ǰ������֤����17λ���ֱ���Բ�ͬ��ϵ�����ӵ�һλ����ʮ��λ��ϵ���ֱ�Ϊ��7��9��10��5��8��4��2��1��6��3��7��9��10��5��8��4��2��
     * ����17λ���ֺ�ϵ����˵Ľ����ӡ�
     * �üӳ����ͳ���11���������Ƕ��٣�
     * ����ֻ������0��1��2��3��4��5��6��7��8��9��10��11�����֡�
     * ��ֱ��Ӧ�����һλ���֤�ĺ���Ϊ1��0��X ��9��8��7��6��5��4��3��2��
     */
    public static String verificationCode(String str17) {
        char[] chars = str17.toCharArray();
        if (chars.length < 17) {
            return " ";
        }
        // ǰʮ��λ�ֱ��Ӧ��ϵ��
        int[] coefficient = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        // ���Ӧ��ȡ�õĵ�ʮ��λ����֤��
        char[] resultChar = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int[] numberArr = new int[17];
        int result = 0;
        for (int i = 0; i < numberArr.length; i++) {
            numberArr[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < numberArr.length; i++) {
            result += coefficient[i] * numberArr[i];
        }
        return String.valueOf(resultChar[result % 11]);
    }
}
