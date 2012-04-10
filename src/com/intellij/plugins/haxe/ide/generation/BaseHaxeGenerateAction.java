package com.intellij.plugins.haxe.ide.generation;

import com.intellij.lang.javascript.generation.BaseJSGenerateHandler;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.plugins.haxe.lang.psi.HaxeClass;
import com.intellij.plugins.haxe.lang.psi.HaxeFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;

/**
 * @author: Fedor.Korotkov
 */
public abstract class BaseHaxeGenerateAction extends AnAction {

  public void actionPerformed(final AnActionEvent e) {
    final Project project = e.getData(PlatformDataKeys.PROJECT);
    assert project != null;
    final Pair<Editor, PsiFile> editorAndPsiFile = getEditorAndPsiFile(e);
    getGenerateHandler().invoke(project, editorAndPsiFile.first, editorAndPsiFile.second);
  }

  private static Pair<Editor, PsiFile> getEditorAndPsiFile(final AnActionEvent e) {
    final Project project = e.getData(PlatformDataKeys.PROJECT);
    if (project == null) return Pair.create(null, null);
    Editor editor = e.getData(PlatformDataKeys.EDITOR);
    PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
    return Pair.create(editor, psiFile);
  }

  protected abstract BaseHaxeGenerateHandler getGenerateHandler();

  @Override
  public void update(final AnActionEvent e) {
    final Pair<Editor, PsiFile> editorAndPsiFile = getEditorAndPsiFile(e);
    final VirtualFile file = e.getData(PlatformDataKeys.VIRTUAL_FILE);
    final Editor editor = editorAndPsiFile.first;
    final PsiFile psiFile = editorAndPsiFile.second;

    final int caretOffset = editor.getCaretModel().getOffset();
    final boolean inClass = PsiTreeUtil.getParentOfType(psiFile.findElementAt(caretOffset), HaxeClass.class) != null;

    e.getPresentation().setEnabled(inClass);
    e.getPresentation().setVisible(inClass);
  }
}
