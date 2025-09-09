package dev.corbie.api;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LoremImageController {
    private String defaultColor = "ffd801";

    @RequestMapping("/loremimage")
    public ResponseEntity<byte[]> downloadSvgImage() {
        return ResponseEntity.ok()
            .headers(getHeader())
            .body(getSVG(400,300,defaultColor).getBytes());
    }

    @RequestMapping("/loremimage/{width}")
    public ResponseEntity<byte[]> downloadSvgImage(@PathVariable String width) {
        return ResponseEntity.ok()
            .headers(getHeader())
            .body(getSVG(Integer.parseInt(width),Integer.parseInt(width),defaultColor).getBytes());
    }

    @RequestMapping("/loremimage/{width}/{height}")
    public ResponseEntity<byte[]> downloadSvgImage(@PathVariable String width, @PathVariable String height) {
        return ResponseEntity.ok()
            .headers(getHeader())
            .body(getSVG(Integer.parseInt(width),Integer.parseInt(height),defaultColor).getBytes());
    }

    @RequestMapping("/loremimage/{width}/{height}/{color}")
    public ResponseEntity<byte[]> downloadSvgImage(@PathVariable String width, @PathVariable String height, @PathVariable String color) {
        return ResponseEntity.ok()
            .headers(getHeader())
            .body(getSVG(Integer.parseInt(width),Integer.parseInt(height),color).getBytes());
    }

    private HttpHeaders getHeader() {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type","image/svg+xml");
        return header;
    }

    private String getSVG(Integer width, Integer height, String color) {
        Integer fontSize = getFontSize(width, height);
        String textColor = getTextColor(color);
        Integer logoWidth = getLogoWidth(width, height);
        Integer logoHeight = Math.round((float) logoWidth / 55 * 64);

        String svgText = "<text text-anchor='middle' alignment-baseline='middle' x='%s' y='%s' font-size='%s' fill='%s'>%s</text>";

        return new StringBuilder()
            .append("<?xml version='1.0' encoding='utf-8'?>")
            .append(String.format("<svg width='%s' height='%s' xmlns='http://www.w3.org/2000/svg'>", width, height))
            .append(String.format("<style>@font-face {font-family: 'SourceCode';src:url('/fonts/SourceCodePro-Regular.otf.woff')format('woff2'),url('/fonts/SourceCodePro-Regular.otf.woff2')format('woff2');} * {font: normal %spx 'SourceCode';}</style>", fontSize))
            .append(String.format("<rect x='0' y='0' width='%s' height='%s' fill='#%s'/>",width, height, color))
            .append(String.format(svgText, (width/2), ((height / 2) - fontSize), fontSize, textColor, width))
            .append(String.format(svgText, (width/2), (height / 2), fontSize, textColor, "x"))
            .append(String.format(svgText, (width/2), ((height / 2) + fontSize), fontSize, textColor, height))
            .append(String.format("<svg x='%s' y='%s' width='%s' height='%s' viewBox='0 0 550 640'><g stroke='#000' stroke-width='10'><g fill='#444'><circle cx='270' cy='370' r='250'/><circle cx='270' cy='220' r='200'/></g><g fill='#fff'><circle cx='195' cy='175' r='70'/><circle cx='345' cy='175' r='70'/></g></g><circle cx='195' cy='175' r='40'/><circle cx='345' cy='175' r='40'/><path d='m145 370c0-175 250-175 250 0l-125 250z' fill='#ffd801' stroke='#000' stroke-linejoin='round' stroke-width='10'/></svg>", (width - logoWidth - 10), (height - logoHeight - 10), logoWidth, logoHeight))
            .append("</svg>")
            .toString();
    }

    private Integer getFontSize(Integer width, Integer height) {
        int fontSize = (width / 20);
        if (fontSize < 20) {
            fontSize = 20;
        }
        if ((height / 4) < fontSize) {
            fontSize = (height / 4);
        }
        return fontSize;
    }

    private String getTextColor(String bgColor) {
        String textColor = "white";
        Integer r = Integer.valueOf(bgColor.substring(0, 2), 16);
        Integer g = Integer.valueOf(bgColor.substring(2, 4), 16);
        Integer b = Integer.valueOf(bgColor.substring(4, 6), 16);

        Integer o = (r * 299 + g * 587 + b * 114) / 1000;

        if (o > 125) {
            textColor = "black";
        }

        return textColor;
    }

    private Integer getLogoWidth(Integer width, Integer height) {
        // 550 x 640
        Integer basis = Math.min(width, height);
        Integer ratioDiff = Math.max(width, height) / Math.min(width, height);
        Double logoWidth = 0.055 * basis * 3;

        if (ratioDiff > 7.5) {
            logoWidth = Math.min(width, height) * 0.5;
        }

        return (int) Math.round(logoWidth);
    }
}
