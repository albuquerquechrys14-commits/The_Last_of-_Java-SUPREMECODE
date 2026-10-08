public class Material extends Item {
    public Material() {
        super("Material", "Tecido e álcool. 2 materiais criam 1 Kit Médico");
    }

    @Override
    public boolean usar(Sobrevivente usuario) {
        System.out.println("Materiais não são usados sozinhos. Use a opção 'Criar kit médico'.");
        return false;
    }
}
