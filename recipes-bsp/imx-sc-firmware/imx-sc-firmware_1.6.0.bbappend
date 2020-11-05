FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += " file://mx8qx-icore-scfw-tcm.bin \
                    file://mx8qx-smarcore-scfw-tcm.bin"

do_deploy_prepend() {
      cp ../mx8qx-icore-scfw-tcm.bin .
      cp ../mx8qx-smarcore-scfw-tcm.bin .
}
