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

/**
 * Extension for MermaidJS.
 *
 * @author Thibault Meyer
 * @since 18.02.24
 */
public final class MermaidJsExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {

    /**
     * Build a default instance.
     */
    private MermaidJsExtension() {
    }

    /**
     * Create a new instance of {@code MermaidJsExtension}.
     *
     * @return Newly created instance
     */
    public static Extension create() {
        return new MermaidJsExtension();
    }

    @Override
    public void extend(final Parser.Builder builder) {
        builder.customBlockParserFactory(
            new MermaidJsBlockParser.Factory()
        );
    }

    @Override
    public void extend(final HtmlRenderer.Builder rendererBuilder) {
        rendererBuilder
            .escapeHtml(false)
            .nodeRendererFactory(MermaidJsBlockRenderer::new);
    }
}
