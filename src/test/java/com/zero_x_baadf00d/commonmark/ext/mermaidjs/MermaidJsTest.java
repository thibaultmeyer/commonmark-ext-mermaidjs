/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Thibault Meyer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zero_x_baadf00d.commonmark.ext.mermaidjs;

import org.commonmark.Extension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.testutil.RenderingTestCase;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

public class MermaidJsTest extends RenderingTestCase {

    private static final Set<Extension> EXTENSIONS = Collections
        .singleton(MermaidJsExtension.create());

    private static final Parser PARSER = Parser
        .builder()
        .extensions(EXTENSIONS)
        .build();

    private static final HtmlRenderer RENDERER = HtmlRenderer
        .builder()
        .extensions(EXTENSIONS)
        .build();

    @Override
    protected String render(final String source) {
        return RENDERER.render(PARSER.parse(source));
    }

    @Test
    public void mermaidJs_001() {
        final String input = "{% mermaidjs %}" +
            "\ngraph TD;" +
            "\nA-->B;" +
            "\nA-->C;" +
            "\n{% mermaidjs %}" +
            "\n";
        assertRendering(input, "<div align=\"center\" class=\"mermaid\">graph TD;A-->B;A-->C;</div>");
    }

    @Test
    public void mermaidJs_002() {
        final String input = "{% mermaidjs %}" +
            "\ngraph TD;" +
            "\nA-->B;" +
            "\nA-->C;" +
            "\n{% mermaidjs %}" +
            "\n" +
            "{% mermaidjs %}" +
            "\ngraph TD;" +
            "\nA-->C;" +
            "\nA-->B;" +
            "\n{% mermaidjs %}" +
            "\n";
        assertRendering(input, "<div align=\"center\" class=\"mermaid\">graph TD;A-->B;A-->C;</div><div align=\"center\" class=\"mermaid\">graph TD;A-->C;A-->B;</div>");
    }

    @Test
    public void mermaidJs_003() {
        final String input = "<h1>Hello</h1>" +
            "\n\n{% mermaidjs %}" +
            "\ngraph TD;" +
            "\nA-->B;" +
            "\nA-->C;" +
            "\n{% mermaidjs %}" +
            "\n";
        assertRendering(input, "<h1>Hello</h1>\n<div align=\"center\" class=\"mermaid\">graph TD;A-->B;A-->C;</div>");
    }
}
