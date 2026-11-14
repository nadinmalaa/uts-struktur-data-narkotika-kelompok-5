package service;

import datastructure.SinglyLinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Provenance;
import model.Putusan;
import model.Terdakwa;

public class CsvParser {
    public static SinglyLinkedList<Putusan> parseCsv(String filePath) {
        return parsePutusan(filePath);
    }

    private static String[] parseCsvLine(String line) {
        List<String> tokens = new ArrayList();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        tokens.add(sb.toString());
        String[] result = new String[tokens.size()];

        for(int i = 0; i < tokens.size(); ++i) {
            String val = ((String)tokens.get(i)).trim();
            if (val.startsWith("\"") && val.endsWith("\"") && val.length() >= 2) {
                val = val.substring(1, val.length() - 1).trim();
            }

            result[i] = val;
        }

        return result;
    }

    public static SinglyLinkedList<Putusan> parsePutusan(String filePath) {
        SinglyLinkedList<Putusan> list = new SinglyLinkedList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            int lineNumber = 0;

            String line;
            while((line = br.readLine()) != null) {
                ++lineNumber;
                if (!line.trim().isEmpty()) {
                    if (isHeader) {
                        isHeader = false;
                    } else {
                        String[] values = parseCsvLine(line);
                        if (values.length >= 4) {
                            try {
                                Putusan p = new Putusan();
                                p.setCaseId(values[0].trim());
                                p.setNomorPerkara(values[1].trim());
                                p.setPengadilan(values[2].trim());

                                try {
                                    p.setTanggalPutusan(LocalDate.parse(values[3].trim()));
                                } catch (Exception var10) {
                                    p.setTanggalPutusan(LocalDate.now());
                                }

                                list.add(p);
                            } catch (Exception e) {
                                System.err.println("Peringatan Putusan baris " + lineNumber + " korup: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file Putusan CSV: " + e.getMessage());
        }

        return list;
    }

    public static SinglyLinkedList<Terdakwa> parseTerdakwa(String filePath) {
        SinglyLinkedList<Terdakwa> list = new SinglyLinkedList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            int lineNumber = 0;

            String line;
            while((line = br.readLine()) != null) {
                ++lineNumber;
                if (!line.trim().isEmpty()) {
                    if (isHeader) {
                        isHeader = false;
                    } else {
                        String[] values = parseCsvLine(line);
                        if (values.length >= 4) {
                            try {
                                Terdakwa t = new Terdakwa();
                                t.setCaseId(values[0].trim());
                                t.setKodeTerdakwa(values[1].trim());
                                t.setTuntutanBulan(Integer.parseInt(values[2].trim()));
                                t.setVonisBulan(Integer.parseInt(values[3].trim()));
                                if (values.length > 4) {
                                    t.setCatatan(values[4].trim());
                                }

                                list.add(t);
                            } catch (Exception e) {
                                System.err.println("Peringatan Terdakwa baris " + lineNumber + " korup: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file Terdakwa CSV: " + e.getMessage());
        }

        return list;
    }

    public static SinglyLinkedList<Provenance> parseProvenance(String filePath) {
        SinglyLinkedList<Provenance> list = new SinglyLinkedList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isHeader = true;
            int lineNumber = 0;

            String line;
            while((line = br.readLine()) != null) {
                ++lineNumber;
                if (!line.trim().isEmpty()) {
                    if (isHeader) {
                        isHeader = false;
                    } else {
                        String[] values = parseCsvLine(line);
                        if (values.length >= 8) {
                            try {
                                Provenance prov = new Provenance();
                                prov.setCaseId(values[0].trim());
                                prov.setSourceFile(values[1].trim());
                                prov.setSourceCourt(values[2].trim());
                                prov.setSourcePages(values[3].trim());
                                prov.setExtractor(values[4].trim());
                                prov.setReviewer(values[5].trim());
                                prov.setExtractionMethod(values[6].trim());
                                prov.setVerificationStatus(values[7].trim());
                                list.add(prov);
                            } catch (Exception e) {
                                System.err.println("Peringatan Provenance baris " + lineNumber + " korup: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file Provenance CSV: " + e.getMessage());
        }

        return list;
    }
}