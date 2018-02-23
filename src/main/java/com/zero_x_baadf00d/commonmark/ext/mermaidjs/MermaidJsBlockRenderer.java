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

import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Renderer for MermaidJs block.
 *
 * @author Thibault Meyer
 * @since 18.02.24
 */
public class MermaidJsBlockRenderer implements NodeRenderer {

    /**
     * The current Html writer.
     */
    private final HtmlWriter html;

    /**
     * Build a default instance/
     *
     * @param context The renderer context
     */
    public MermaidJsBlockRenderer(final HtmlNodeRendererContext context) {
        this.html = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(MermaidJsBlock.class);
    }

    @Override
    public void render(final Node node) {
        if (node instanceof MermaidJsBlock) {
            final Map<String, String> properties = new HashMap<>();
            properties.put("class", "mermaid");
            properties.put("align", "center");
            html.tag("div", properties);
        }
        MermaidJsNode n = (MermaidJsNode) node.getFirstChild();
        while (n != null) {
            final MermaidJsNode next = (MermaidJsNode) n.getNext();
            if (n.getData().endsWith(";")) {
                html.raw(n.getData());
            } else {
                html.raw(n.getData() + ";");
            }
            n = next;
        }
        if (node instanceof MermaidJsBlock) {
            html.tag("/div");
        }
    }
}
