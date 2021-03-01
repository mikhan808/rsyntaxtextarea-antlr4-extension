/*-
 * #%L
 * RSyntaxTextArea ANTLR 4 Extension
 * %%
 * Copyright (C) 2021 Markus Heberling
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package de.tisoft.rsyntaxtextarea.modes.demo;

import de.tisoft.rsyntaxtextarea.modes.antlr.AntlrTokenMaker;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Lexer;
import org.fife.ui.rsyntaxtextarea.Token;
import third_party.asm6502Lexer;

public class AssemblerTokenMaker extends AntlrTokenMaker {
  @Override
  protected Lexer createLexer(String text) {
    return new asm6502Lexer(CharStreams.fromString(text)) {
      @Override
      public void skip() {
        setChannel(HIDDEN);
      }
    };
  }

  @Override
  protected int convertType(int type) {
    switch (type) {
      case asm6502Lexer.COMMENT:
        return Token.COMMENT_EOL;
      case asm6502Lexer.NUMBER:
        return Token.LITERAL_NUMBER_DECIMAL_INT;
      case asm6502Lexer.STRING:
        return Token.LITERAL_STRING_DOUBLE_QUOTE;
      case asm6502Lexer.ASSEMBLER_INSTRUCTION:
        return Token.RESERVED_WORD;
    }
    return Token.IDENTIFIER;
  }

  @Override
  public String[] getLineCommentStartAndEnd(int languageIndex) {
    return new String[] {";", null};
  }
}
